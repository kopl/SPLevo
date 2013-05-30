package org.splevo.vpm.analyzer.semantic;

import java.util.List;

import org.apache.log4j.Logger;
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
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.Initializer;
import org.eclipse.gmt.modisco.java.InstanceofExpression;
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
    
	/** This {@link StringBuilder} is used to store the content. */
	private StringBuilder sb;

    public IndexASTNodeSwitch(){
    	clear();
    }
    
    /** Clears the text content. */
    public void clear(){
    	sb = new StringBuilder();
    }
    
    private void add(String content){
    	sb.append(content + " ");
    	System.out.println("\\" + content);
    }
    
    /**
     * @return The text content collected from nodes since the last clear() call.
     */
    public String getContent(){
    	return sb.toString();
    }

	@Override
	public List<ASTNode> casePackage(Package object) {
		add(object.getName());
		return super.casePackage(object);
	}

	@Override
	public List<ASTNode> caseNamedElement(NamedElement object) {
		add(object.getName());
		return super.caseNamedElement(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedItem(UnresolvedItem object) {
		add(object.getName());
		return super.caseUnresolvedItem(object);
	}

	@Override
	public List<ASTNode> caseComment(Comment object) {
		add(object.getContent());
		return super.caseComment(object);
	}

	@Override
	public List<ASTNode> caseBlockComment(BlockComment object) {
		add(object.getContent());
		return super.caseBlockComment(object);
	}

	@Override
	public List<ASTNode> caseJavadoc(Javadoc object) {
		add(object.getContent());
		return super.caseJavadoc(object);
	}

	@Override
	public List<ASTNode> caseLineComment(LineComment object) {
		add(object.getContent());
		return super.caseLineComment(object);
	}

	@Override
	public List<ASTNode> caseManifestAttribute(ManifestAttribute object) {
		add(object.getKey());
		add(object.getValue());
		return super.caseManifestAttribute(object);
	}

	@Override
	public List<ASTNode> caseManifestEntry(ManifestEntry object) {
		add(object.getName());
		return super.caseManifestEntry(object);
	}

	@Override
	public List<ASTNode> caseMethodRefParameter(MethodRefParameter object) {
		add(object.getName());
		return super.caseMethodRefParameter(object);
	}

	@Override
	public List<ASTNode> caseTagElement(TagElement object) {
		add(object.getTagName());
		return super.caseTagElement(object);
	}

	@Override
	public List<ASTNode> caseTextElement(TextElement object) {
		add(object.getText());
		return super.caseTextElement(object);
	}

	@Override
	public List<ASTNode> caseAbstractTypeDeclaration(
			AbstractTypeDeclaration object) {
		add(object.getName());
		return super.caseAbstractTypeDeclaration(object);
	}

	@Override
	public List<ASTNode> caseAnnotationTypeDeclaration(
			AnnotationTypeDeclaration object) {
		add(object.getName());
		return super.caseAnnotationTypeDeclaration(object);
	}

	@Override
	public List<ASTNode> caseArrayType(ArrayType object) {
		add(object.getName());
		return super.caseArrayType(object);
	}

	@Override
	public List<ASTNode> caseClassDeclaration(ClassDeclaration object) {
		add(object.getName());
		return super.caseClassDeclaration(object);
	}

	@Override
	public List<ASTNode> caseEnumDeclaration(EnumDeclaration object) {
		add(object.getName());
		return super.caseEnumDeclaration(object);
	}

	@Override
	public List<ASTNode> caseInterfaceDeclaration(InterfaceDeclaration object) {
		add(object.getName());
		return super.caseInterfaceDeclaration(object);
	}

	@Override
	public List<ASTNode> caseParameterizedType(ParameterizedType object) {
		add(object.getName());
		return super.caseParameterizedType(object);
	}

	@Override
	public List<ASTNode> casePrimitiveType(PrimitiveType object) {
		add(object.getName());
		return super.casePrimitiveType(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeBoolean(PrimitiveTypeBoolean object) {
		add(object.getName());
		return super.casePrimitiveTypeBoolean(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeByte(PrimitiveTypeByte object) {
		add(object.getName());
		return super.casePrimitiveTypeByte(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeChar(PrimitiveTypeChar object) {
		add(object.getName());
		return super.casePrimitiveTypeChar(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeDouble(PrimitiveTypeDouble object) {
		add(object.getName());
		return super.casePrimitiveTypeDouble(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeShort(PrimitiveTypeShort object) {
		add(object.getName());
		return super.casePrimitiveTypeShort(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeFloat(PrimitiveTypeFloat object) {
		add(object.getName());
		return super.casePrimitiveTypeFloat(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeInt(PrimitiveTypeInt object) {
		add(object.getName());
		return super.casePrimitiveTypeInt(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeLong(PrimitiveTypeLong object) {
		add(object.getName());
		return super.casePrimitiveTypeLong(object);
	}

	@Override
	public List<ASTNode> casePrimitiveTypeVoid(PrimitiveTypeVoid object) {
		add(object.getName());
		return super.casePrimitiveTypeVoid(object);
	}

	@Override
	public List<ASTNode> caseType(Type object) {
		add(object.getName());
		return super.caseType(object);
	}

	@Override
	public List<ASTNode> caseTypeDeclaration(TypeDeclaration object) {
		add(object.getName());
		return super.caseTypeDeclaration(object);
	}

	@Override
	public List<ASTNode> caseTypeParameter(TypeParameter object) {
		add(object.getName());
		return super.caseTypeParameter(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedAnnotationDeclaration(
			UnresolvedAnnotationDeclaration object) {
		add(object.getName());
		return super.caseUnresolvedAnnotationDeclaration(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedClassDeclaration(
			UnresolvedClassDeclaration object) {
		add(object.getName());
		return super.caseUnresolvedClassDeclaration(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedEnumDeclaration(
			UnresolvedEnumDeclaration object) {
		add(object.getName());
		return super.caseUnresolvedEnumDeclaration(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedInterfaceDeclaration(
			UnresolvedInterfaceDeclaration object) {
		add(object.getName());
		return super.caseUnresolvedInterfaceDeclaration(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedType(UnresolvedType object) {
		add(object.getName());
		return super.caseUnresolvedType(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedTypeDeclaration(
			UnresolvedTypeDeclaration object) {
		add(object.getName());
		return super.caseUnresolvedTypeDeclaration(object);
	}

	@Override
	public List<ASTNode> caseWildCardType(WildCardType object) {
		add(object.getName());
		return super.caseWildCardType(object);
	}

	@Override
	public List<ASTNode> caseAbstractMethodDeclaration(
			AbstractMethodDeclaration object) {
		add(object.getName());
		return super.caseAbstractMethodDeclaration(object);
	}

	@Override
	public List<ASTNode> caseAnnotationMemberValuePair(
			AnnotationMemberValuePair object) {
		add(object.getName());
		return super.caseAnnotationMemberValuePair(object);
	}

	@Override
	public List<ASTNode> caseAnnotationTypeMemberDeclaration(
			AnnotationTypeMemberDeclaration object) {
		add(object.getName());
		return super.caseAnnotationTypeMemberDeclaration(object);
	}

	@Override
	public List<ASTNode> caseBodyDeclaration(BodyDeclaration object) {
		add(object.getName());
		return super.caseBodyDeclaration(object);
	}

	@Override
	public List<ASTNode> caseConstructorDeclaration(
			ConstructorDeclaration object) {
		add(object.getName());
		return super.caseConstructorDeclaration(object);
	}

	@Override
	public List<ASTNode> caseEnumConstantDeclaration(
			EnumConstantDeclaration object) {
		add(object.getName());
		return super.caseEnumConstantDeclaration(object);
	}

	@Override
	public List<ASTNode> caseFieldDeclaration(FieldDeclaration object) {
		add(object.getName());
		return super.caseFieldDeclaration(object);
	}

	@Override
	public List<ASTNode> caseInitializer(Initializer object) {
		add(object.getName());
		return super.caseInitializer(object);
	}

	@Override
	public List<ASTNode> caseMethodDeclaration(MethodDeclaration object) {
		add(object.getName());
		return super.caseMethodDeclaration(object);
	}

	@Override
	public List<ASTNode> caseSingleVariableDeclaration(
			SingleVariableDeclaration object) {
		add(object.getName());
		return super.caseSingleVariableDeclaration(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedAnnotationTypeMemberDeclaration(
			UnresolvedAnnotationTypeMemberDeclaration object) {
		add(object.getName());
		return super.caseUnresolvedAnnotationTypeMemberDeclaration(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedMethodDeclaration(
			UnresolvedMethodDeclaration object) {
		add(object.getName());
		return super.caseUnresolvedMethodDeclaration(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedVariableDeclarationFragment(
			UnresolvedVariableDeclarationFragment object) {
		add(object.getName());
		return super.caseUnresolvedVariableDeclarationFragment(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedSingleVariableDeclaration(
			UnresolvedSingleVariableDeclaration object) {
		add(object.getName());
		return super.caseUnresolvedSingleVariableDeclaration(object);
	}

	@Override
	public List<ASTNode> caseVariableDeclaration(VariableDeclaration object) {
		add(object.getName());
		return super.caseVariableDeclaration(object);
	}

	@Override
	public List<ASTNode> caseVariableDeclarationFragment(
			VariableDeclarationFragment object) {
		add(object.getName());
		return super.caseVariableDeclarationFragment(object);
	}

	@Override
	public List<ASTNode> caseArchive(Archive object) {
		add(object.getName());
		return super.caseArchive(object);
	}

	@Override
	public List<ASTNode> caseCharacterLiteral(CharacterLiteral object) {
		add('|' + object.getEscapedValue() + '|');
		return super.caseCharacterLiteral(object);
	}

	@Override
	public List<ASTNode> caseClassFile(ClassFile object) {
		add(object.getName());
		return super.caseClassFile(object);
	}

	@Override
	public List<ASTNode> caseInstanceofExpression(InstanceofExpression object) {
		add("instanceof");
		return super.caseInstanceofExpression(object);
	}

	@Override
	public List<ASTNode> caseNumberLiteral(NumberLiteral object) {
		add(object.getTokenValue());
		return super.caseNumberLiteral(object);
	}

	@Override
	public List<ASTNode> caseStringLiteral(StringLiteral object) {
		add(object.getEscapedValue());
		return super.caseStringLiteral(object);
	}

	@Override
	public List<ASTNode> caseLabeledStatement(LabeledStatement object) {
		add(object.getName());
		return super.caseLabeledStatement(object);
	}

	@Override
	public List<ASTNode> caseUnresolvedLabeledStatement(
			UnresolvedLabeledStatement object) {
		add(object.getName());
		return super.caseUnresolvedLabeledStatement(object);
	}
	
	@Override
	public List<ASTNode> defaultCase(EObject object) {
		logger.warn("Unrecognized ASTNode: " + object.toString());
		return super.defaultCase(object);
	}


//		private void print(EObject object) {
//		Resource resource = new ResourceImpl();
//		resource.getContents().add(EcoreUtil.copy(object));
//		try {
//			System.out.println("##################################");
//			resource.save(System.out, null);
//			System.out.println("##################################");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
}
