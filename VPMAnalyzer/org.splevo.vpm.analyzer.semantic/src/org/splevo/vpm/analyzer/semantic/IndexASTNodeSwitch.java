package org.splevo.vpm.analyzer.semantic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.AnnotationTypeDeclaration;
import org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.Archive;
import org.eclipse.gmt.modisco.java.ArrayType;
import org.eclipse.gmt.modisco.java.BlockComment;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.CharacterLiteral;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.ClassFile;
import org.eclipse.gmt.modisco.java.Comment;
import org.eclipse.gmt.modisco.java.ConstructorDeclaration;
import org.eclipse.gmt.modisco.java.EnumConstantDeclaration;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.Initializer;
import org.eclipse.gmt.modisco.java.InterfaceDeclaration;
import org.eclipse.gmt.modisco.java.Javadoc;
import org.eclipse.gmt.modisco.java.LabeledStatement;
import org.eclipse.gmt.modisco.java.LineComment;
import org.eclipse.gmt.modisco.java.ManifestAttribute;
import org.eclipse.gmt.modisco.java.ManifestEntry;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.MethodRefParameter;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.gmt.modisco.java.NumberLiteral;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.ParameterizedType;
import org.eclipse.gmt.modisco.java.PrimitiveType;
import org.eclipse.gmt.modisco.java.PrimitiveTypeBoolean;
import org.eclipse.gmt.modisco.java.PrimitiveTypeByte;
import org.eclipse.gmt.modisco.java.PrimitiveTypeChar;
import org.eclipse.gmt.modisco.java.PrimitiveTypeDouble;
import org.eclipse.gmt.modisco.java.PrimitiveTypeFloat;
import org.eclipse.gmt.modisco.java.PrimitiveTypeInt;
import org.eclipse.gmt.modisco.java.PrimitiveTypeLong;
import org.eclipse.gmt.modisco.java.PrimitiveTypeShort;
import org.eclipse.gmt.modisco.java.PrimitiveTypeVoid;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.StringLiteral;
import org.eclipse.gmt.modisco.java.TagElement;
import org.eclipse.gmt.modisco.java.TextElement;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeDeclaration;
import org.eclipse.gmt.modisco.java.TypeParameter;
import org.eclipse.gmt.modisco.java.UnresolvedAnnotationDeclaration;
import org.eclipse.gmt.modisco.java.UnresolvedAnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.UnresolvedClassDeclaration;
import org.eclipse.gmt.modisco.java.UnresolvedEnumDeclaration;
import org.eclipse.gmt.modisco.java.UnresolvedInterfaceDeclaration;
import org.eclipse.gmt.modisco.java.UnresolvedItem;
import org.eclipse.gmt.modisco.java.UnresolvedLabeledStatement;
import org.eclipse.gmt.modisco.java.UnresolvedMethodDeclaration;
import org.eclipse.gmt.modisco.java.UnresolvedSingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.UnresolvedType;
import org.eclipse.gmt.modisco.java.UnresolvedTypeDeclaration;
import org.eclipse.gmt.modisco.java.UnresolvedVariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclaration;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.WildCardType;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;

/**
 * This switch extracts the text contents from the given nodes.
 * 
 * @author Daniel Kojic
 * 
 */
public class IndexASTNodeSwitch extends JavaSwitch<List<ASTNode>> {

    /** The logger for this class. */
    private Logger logger = Logger.getLogger(IndexASTNodeSwitch.class);
    
	/** This {@link StringBuilder} is used to store the code. */
	private StringBuilder contents;

	/** This {@link StringBuilder} is used to store the comment. */
	private StringBuilder comments;
	
    public IndexASTNodeSwitch(){
    	contents = new StringBuilder();
    	comments = new StringBuilder();
    }
    
    /** Clears the text content. */
    public void clear(){
    	contents = new StringBuilder();
    	comments = new StringBuilder();
    }
    
    private void addContent(String content){
    	contents.append(content + " \n");  	
    }
    
    private void addComment(String comment){    	
    	comments.append(comment + " \n");
    }
    
    /**
     * @return The text content collected from nodes since the last clear() call.
     */
    public String getContent(){
    	return contents.toString();
    }
    
    /**
     * @return The text comments collected from nodes since the last clear() call.
     */
    public String getComments(){
    	return comments.toString();
    }
    
    /**
     * Do switch method enhanced to globally remove all null values.
     * {@inheritDoc}
     */
    @Override
    protected List<ASTNode> doSwitch(EClass eClass, EObject eObject) {
        List<ASTNode> nodes = super.doSwitch(eClass, eObject); 
        nodes.removeAll(Collections.singleton(null));
        return new ArrayList<ASTNode>();
    }

	@Override
	public List<ASTNode> casePackage(Package object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.casePackage(object);
	}

	@Override
	public List<ASTNode> caseNamedElement(NamedElement object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseNamedElement(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedItem(UnresolvedItem object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseUnresolvedItem(object);
	}

	@Override
	public List<ASTNode> caseComment(Comment object) {
		addComment(object.getContent());
		return new ArrayList<ASTNode>(); // return super.caseComment(object);
	}

	@Override
	public List<ASTNode> caseBlockComment(BlockComment object) {
		addComment(object.getContent());
		return new ArrayList<ASTNode>(); // return super.caseBlockComment(object);
	}

	@Override
	public List<ASTNode> caseJavadoc(Javadoc object) {
		addComment(object.getContent());
		return new ArrayList<ASTNode>(); // return super.caseJavadoc(object);
	}

	@Override
	public List<ASTNode> caseLineComment(LineComment object) {
		addComment(object.getContent());
		return new ArrayList<ASTNode>(); // return super.caseLineComment(object);
	}

	@Override
	public List<ASTNode> caseManifestAttribute(ManifestAttribute object) {
		addContent(object.getKey());
		addContent(object.getValue());
		return new ArrayList<ASTNode>(); // return super.caseManifestAttribute(object);
	}

	@Override
	public List<ASTNode> caseManifestEntry(ManifestEntry object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseManifestEntry(object);
	}

	@Override
	public List<ASTNode> caseMethodRefParameter(MethodRefParameter object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseMethodRefParameter(object);
	}

	@Override
	public List<ASTNode> caseTagElement(TagElement object) {
		addContent(object.getTagName());
		return new ArrayList<ASTNode>(); // return super.caseTagElement(object);
	}

	@Override
	public List<ASTNode> caseTextElement(TextElement object) {
		addComment(object.getText());
		return new ArrayList<ASTNode>(); // return super.caseTextElement(object);
	}

	@Override
	public List<ASTNode> caseAbstractTypeDeclaration(
			AbstractTypeDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseAbstractTypeDeclaration(object);
	}

	@Override
	public List<ASTNode> caseAnnotationTypeDeclaration(
			AnnotationTypeDeclaration object) {
		addComment(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseAnnotationTypeDeclaration(object);
	}

	@Override
	public List<ASTNode> caseArrayType(ArrayType object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseArrayType(object);
	}

	@Override
	public List<ASTNode> caseClassDeclaration(ClassDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseClassDeclaration(object);
	}

	@Override
	public List<ASTNode> caseEnumDeclaration(EnumDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseEnumDeclaration(object);
	}

	@Override
	public List<ASTNode> caseInterfaceDeclaration(InterfaceDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseInterfaceDeclaration(object);
	}

	@Override
	public List<ASTNode> caseParameterizedType(ParameterizedType object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseParameterizedType(object);
	}

	@Override
	public List<ASTNode> casePrimitiveType(PrimitiveType object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.casePrimitiveType(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeBoolean(PrimitiveTypeBoolean object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.casePrimitiveTypeBoolean(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeByte(PrimitiveTypeByte object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.casePrimitiveTypeByte(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeChar(PrimitiveTypeChar object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.casePrimitiveTypeChar(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeDouble(PrimitiveTypeDouble object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.casePrimitiveTypeDouble(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeShort(PrimitiveTypeShort object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.casePrimitiveTypeShort(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeFloat(PrimitiveTypeFloat object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.casePrimitiveTypeFloat(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeInt(PrimitiveTypeInt object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.casePrimitiveTypeInt(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeLong(PrimitiveTypeLong object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.casePrimitiveTypeLong(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeVoid(PrimitiveTypeVoid object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.casePrimitiveTypeVoid(object);
	}

	@Override
	public List<ASTNode> caseType(Type object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseType(object);
	}

	@Override
	public List<ASTNode> caseTypeDeclaration(TypeDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseTypeDeclaration(object);
	}

	@Override
	public List<ASTNode> caseTypeParameter(TypeParameter object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseTypeParameter(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedAnnotationDeclaration(
			UnresolvedAnnotationDeclaration object) {
		addComment(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseUnresolvedAnnotationDeclaration(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedClassDeclaration(
			UnresolvedClassDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseUnresolvedClassDeclaration(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedEnumDeclaration(
			UnresolvedEnumDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseUnresolvedEnumDeclaration(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedInterfaceDeclaration(
			UnresolvedInterfaceDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseUnresolvedInterfaceDeclaration(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedType(UnresolvedType object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseUnresolvedType(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedTypeDeclaration(
			UnresolvedTypeDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseUnresolvedTypeDeclaration(object);
	}

	@Override
	public List<ASTNode> caseWildCardType(WildCardType object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseWildCardType(object);
	}

	@Override
	public List<ASTNode> caseAbstractMethodDeclaration(
			AbstractMethodDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseAbstractMethodDeclaration(object);
	}

	@Override
	public List<ASTNode> caseAnnotationMemberValuePair(
			AnnotationMemberValuePair object) {
		addComment(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseAnnotationMemberValuePair(object);
	}

	@Override
	public List<ASTNode> caseAnnotationTypeMemberDeclaration(
			AnnotationTypeMemberDeclaration object) {
		addComment(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseAnnotationTypeMemberDeclaration(object);
	}

	@Override
	public List<ASTNode> caseBodyDeclaration(BodyDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseBodyDeclaration(object);
	}

	@Override
	public List<ASTNode> caseConstructorDeclaration(
			ConstructorDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseConstructorDeclaration(object);
	}

	@Override
	public List<ASTNode> caseEnumConstantDeclaration(
			EnumConstantDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseEnumConstantDeclaration(object);
	}

	@Override
	public List<ASTNode> caseFieldDeclaration(FieldDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseFieldDeclaration(object);
	}

	@Override
	public List<ASTNode> caseInitializer(Initializer object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseInitializer(object);
	}

	@Override
	public List<ASTNode> caseMethodDeclaration(MethodDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseMethodDeclaration(object);
	}

	@Override
	public List<ASTNode> caseSingleVariableDeclaration(
			SingleVariableDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseSingleVariableDeclaration(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedAnnotationTypeMemberDeclaration(
			UnresolvedAnnotationTypeMemberDeclaration object) {
		addComment(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseUnresolvedAnnotationTypeMemberDeclaration(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedMethodDeclaration(
			UnresolvedMethodDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseUnresolvedMethodDeclaration(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedVariableDeclarationFragment(
			UnresolvedVariableDeclarationFragment object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseUnresolvedVariableDeclarationFragment(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedSingleVariableDeclaration(
			UnresolvedSingleVariableDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseUnresolvedSingleVariableDeclaration(object);
	}

	@Override
	public List<ASTNode> caseVariableDeclaration(VariableDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseVariableDeclaration(object);
	}

	@Override
	public List<ASTNode> caseVariableDeclarationFragment(
			VariableDeclarationFragment object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseVariableDeclarationFragment(object);
	}

	@Override
	public List<ASTNode> caseArchive(Archive object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseArchive(object);
	}

	@Override
	public List<ASTNode> caseCharacterLiteral(CharacterLiteral object) {
		addContent('|' + object.getEscapedValue() + '|');
		return new ArrayList<ASTNode>(); // return super.caseCharacterLiteral(object);
	}

	@Override
	public List<ASTNode> caseClassFile(ClassFile object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseClassFile(object);
	}

	@Override
	public List<ASTNode> caseNumberLiteral(NumberLiteral object) {
		addContent(object.getTokenValue());
		return new ArrayList<ASTNode>(); // return super.caseNumberLiteral(object);
	}

	@Override
	public List<ASTNode> caseStringLiteral(StringLiteral object) {
		addContent(object.getEscapedValue());
		return new ArrayList<ASTNode>(); // return super.caseStringLiteral(object);
	}

	@Override
	public List<ASTNode> caseLabeledStatement(LabeledStatement object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseLabeledStatement(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedLabeledStatement(
			UnresolvedLabeledStatement object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>(); // return super.caseUnresolvedLabeledStatement(object);
	}
	
	@Override
	public List<ASTNode> defaultCase(EObject object) {
		//logger.warn("Unrecognized ASTNode: " + object.toString());
		return new ArrayList<ASTNode>(); // return super.defaultCase(object);
	}
}
