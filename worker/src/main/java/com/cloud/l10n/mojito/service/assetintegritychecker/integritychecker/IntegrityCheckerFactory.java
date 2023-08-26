package com.cloud.l10n.mojito.service.assetintegritychecker.integritychecker;

import com.cloud.l10n.mojito.entity.Asset;
import com.cloud.l10n.mojito.entity.AssetIntegrityChecker;
import com.cloud.l10n.mojito.entity.Repository;
import com.cloud.l10n.mojito.service.assetintegritychecker.AssetIntegrityCheckerRepository;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wyau
 */
@Component
public class IntegrityCheckerFactory {

    @Autowired
    AssetIntegrityCheckerRepository assetIntegrityCheckerRepository;

    @Autowired
    ApplicationContext applicationContext;

    List<DocumentIntegrityChecker> documentIntegrityCheckers = new ArrayList<>();

    @PostConstruct
    private void initAvailableDocumentCheckers() {
        Iterable<DocumentIntegrityChecker> documentIntegrityCheckersIterable =
                applicationContext.getBeansOfType(DocumentIntegrityChecker.class).values();
        documentIntegrityCheckers = Lists.newArrayList(documentIntegrityCheckersIterable);
    }

    /**
     * @param documentExtension The file extension of the document to check
     * @return A list of {@link DocumentIntegrityChecker} supporting the given document extension
     */
    public List<DocumentIntegrityChecker> getDocumentCheckers(String documentExtension) {

        List<DocumentIntegrityChecker> supportedCheckers = new ArrayList<>();

        for (DocumentIntegrityChecker documentIntegrityChecker : documentIntegrityCheckers) {
            if (documentIntegrityChecker.supportsExtension(documentExtension)) {
                supportedCheckers.add(documentIntegrityChecker);
            }
        }

        return supportedCheckers;
    }

    /**
     * @param asset
     * @return Instance of {@link TextUnitIntegrityChecker}, depending on the given asset
     * @throws IntegrityCheckerInstantiationException if unable to create an instance of the integrity
     *                                                checker
     */
    public Set<TextUnitIntegrityChecker> getTextUnitCheckers(Asset asset) {

        Repository repository = asset.getRepository();

        String assetExtension = Files.getFileExtension(asset.getPath());

        Set<AssetIntegrityChecker> assetIntegrityCheckers =
                assetIntegrityCheckerRepository.findByRepositoryAndAssetExtension(
                        repository, assetExtension);
        Set<TextUnitIntegrityChecker> textUnitIntegrityCheckers = new HashSet<>();

        for (AssetIntegrityChecker assetIntegrityChecker : assetIntegrityCheckers) {
            String className = assetIntegrityChecker.getIntegrityCheckerType().getClassName();
            textUnitIntegrityCheckers.add(createInstanceForClassName(className));
        }

        return textUnitIntegrityCheckers;
    }

    /**
     * @param className
     * @return An instance of {@link TextUnitIntegrityChecker} for the given class
     * @throws IntegrityCheckerInstantiationException
     */
    private TextUnitIntegrityChecker createInstanceForClassName(String className)
            throws IntegrityCheckerInstantiationException {
        try {
            Class<?> clazz = Class.forName(className);
            return (TextUnitIntegrityChecker) clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new IntegrityCheckerInstantiationException(
                    "Cannot create an instance of TextUnitIntegrityChecker using reflection", e);
        }
    }
}
