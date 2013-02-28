package org.splevo.diffing.emfcompare.edit.images;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.compare.diff.provider.AbstractDiffExtensionItemProvider;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.gmt.modisco.java.ASTNode;

/**
 * Utility class to handle images for the model elements.
 * @author Benjamin Klatt
 *
 */
public class ImageUtil {

    /** The logger for this class. */
    private static Logger logger = Logger.getLogger(ImageUtil.class);

    /** The internal switch to select the ast node icon. */
    private final static ASTElementIconSwitch astIconSwitch = new ASTElementIconSwitch();
    
    /** The relative path to the insert overlay icon. */
    private static final String OVERLAY_ICON_INSERT = "overlays/insert";
    
    /** The relative path to the delete overlay icon. */
    private static final String OVERLAY_ICON_DELETE = "overlays/delete";
    
    /** The relative path to the default icon. */
    public static final String ICON_DEFAULT = "ast/default";
    
    /** The relative path to the default statement icon. */
    public static final String ICON_STATEMENT = "ast/statement-single";
    
    /** The relative path to the default import icon. */
    public static final String ICON_IMPORT = "ast_modisco/import";
    
    /** The relative path to the default class icon. */
    public static final String ICON_CLASS = "ast_modisco/class/class";
    
    /** The relative path to the default interface icon. */
    public static final String ICON_INTERFACE = "ast_modisco/interfaces/interface";
    
    /** The relative path to the default method icon. */
    public static final String ICON_METHOD = "ast_modisco/methods/public";
    
    /** The relative path to the default field icon. */
    public static final String ICON_FIELD = "ast_modisco/field/fieldDeclaration";
    
    /** The relative path to the default package icon. */
    public static final String ICON_PACKAGE = "ast_modisco/package";
    
    /**
     * Get the icon for a specified ast node.
     * @return The loaded image or null none is available.
     */
    public static Object getASTIcon(ASTNode astNode, AbstractDiffExtensionItemProvider itemProvider){
        
        if(astNode == null){
            logger.warn("null ASTNode supplied to ImageUtil");
            return itemProvider.getResourceLocator().getImage(ICON_DEFAULT);
        }

        String iconPath = astIconSwitch.doSwitch(astNode);
        if(iconPath != null){
            return itemProvider.getResourceLocator().getImage(iconPath);
        }
        
        logger.warn("unable to detect icon path for: "+astNode.toString());
        return itemProvider.getResourceLocator().getImage(ICON_DEFAULT);
    }
    

    /**
     * Compose two images to derive a combined icon.
     * @param baseImage The base image to combine.
     * @param itemProvider The item provider to retrieve the resource locator.
     * @param imagePath The path to the image to be overlaid. (relative to /icons/)
     * @return The composed image object.
     */
    public static Object composeImage(Object baseImage, AbstractDiffExtensionItemProvider itemProvider, String imagePath){
        List<Object> images = new ArrayList<Object>(2);
        images.add(baseImage);
        images.add(itemProvider.getResourceLocator().getImage(imagePath));
        return new ComposedImage(images);
    }


    /**
     * Get an icon for a provided ast node overlaid as delete icon.
     * @param astNode The ast node to get the icon for.
     * @param itemProvider The item provider to access the resources.
     * @return The prepared delete icon.
     */
    public static Object getASTDeleteIcon(ASTNode astNode, AbstractDiffExtensionItemProvider itemProvider) {
        Object baseImage = getASTIcon(astNode, itemProvider);
        return ImageUtil.composeImage(baseImage, itemProvider, OVERLAY_ICON_DELETE);
    }


    /**
     * Get an icon for a provided ast node overlaid as insert icon.
     * 
     * @param astNode The ast node to get the icon for.
     * @param itemProvider The item provider to access the resources.
     * @return The prepared insert icon.
     */
    public static Object getASTInsertIcon(ASTNode astNode, AbstractDiffExtensionItemProvider itemProvider) {
        Object baseImage = getASTIcon(astNode, itemProvider);
        return ImageUtil.composeImage(baseImage, itemProvider, OVERLAY_ICON_INSERT);
    }


    /**
     * Compose an insert icon from a provided base icon.
     * 
     * @param itemProvider The item provider to access the resources.
     * @param iconPath The relative path to the base icon.
     * @return The prepared insert icon.
     */
    public static Object composeInsertIcon(AbstractDiffExtensionItemProvider itemProvider, String iconPath) {
        Object baseIcon = itemProvider.getResourceLocator().getImage(iconPath);
        return ImageUtil.composeImage(baseIcon, itemProvider, OVERLAY_ICON_INSERT);
    }


    /**
     * Compose an delete icon from a provided base icon.
     * 
     * @param itemProvider The item provider to access the resources.
     * @param iconPath The relative path to the base icon.
     * @return The prepared insert icon.
     */
    public static Object composeDeleteIcon(AbstractDiffExtensionItemProvider itemProvider, String iconPath) {
        Object baseIcon = itemProvider.getResourceLocator().getImage(iconPath);
        return ImageUtil.composeImage(baseIcon, itemProvider, OVERLAY_ICON_DELETE);
    }
    
}