package org.splevo.vpm.analyzer.semantic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
 * As this class uses a {@link StringBuilder} to store the content,
 * this class is not threadsafe.
 * 
 * @author Daniel Kojic
 * 
 */
public class IndexASTNodeSwitch extends JavaSwitch<List<ASTNode>> {
    
	/** This {@link StringBuilder} is used to store the code fragments. */
	private StringBuilder contents;

	/** This {@link StringBuilder} is used to store the comments and annotations. */
	private StringBuilder comments;
	
	/** Determines weather comments should be indexed or not. */
	private boolean indexComments;
	
    /**
     * Initializes needed members.
     * 
     * @param indexComments Determines weather comments should be indexed or not.
     */
    public IndexASTNodeSwitch(boolean indexComments) {
    	this.indexComments = indexComments;
    	contents = new StringBuilder();
    	
    	if (indexComments) {
    		comments = new StringBuilder();
    	}
    }
    
    /**
     * Adds Text to the contents {@link StringBuilder}.
     * 
     * @param content 
     */
    private void addContent(String content) {
    	contents.append(content + " \n");  	
    }
    
    /**
     * Adds Text to the comments {@link StringBuilder}.
     * 
     * @param comment The comment to be added. 
     */
    private void addComment(String comment) {
    	if (comment == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	comments.append(comment + " \n");
    }
    
    /**
     * @return The text content collected from nodes since the last clear() call.
     */
    public String getContent() {
    	return contents.toString();
    }
    
    /**
     * @return The text comments collected from nodes since the last clear() call.
     */
    public String getComments() {
    	if (!indexComments) {
    		throw new IllegalStateException("Cannot get comments of this switch. Comments were disabled by constructor parameter.");
    	}
    	
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

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#casePackage(org.eclipse.gmt.modisco.java.Package)
	 */
	@Override
	public List<ASTNode> casePackage(Package object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseNamedElement(org.eclipse.gmt.modisco.java.NamedElement)
	 */
	@Override
	public List<ASTNode> caseNamedElement(NamedElement object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseUnresolvedItem(org.eclipse.gmt.modisco.java.UnresolvedItem)
	 */
	@Override
	public List<ASTNode> caseUnresolvedItem(UnresolvedItem object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseComment(org.eclipse.gmt.modisco.java.Comment)
	 */
	@Override
	public List<ASTNode> caseComment(Comment object) {
		addComment(object.getContent());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseBlockComment(org.eclipse.gmt.modisco.java.BlockComment)
	 */
	@Override
	public List<ASTNode> caseBlockComment(BlockComment object) {
		addComment(object.getContent());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseJavadoc(org.eclipse.gmt.modisco.java.Javadoc)
	 */
	@Override
	public List<ASTNode> caseJavadoc(Javadoc object) {
		addComment(object.getContent());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseLineComment(org.eclipse.gmt.modisco.java.LineComment)
	 */
	@Override
	public List<ASTNode> caseLineComment(LineComment object) {
		addComment(object.getContent());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseManifestAttribute(org.eclipse.gmt.modisco.java.ManifestAttribute)
	 */
	@Override
	public List<ASTNode> caseManifestAttribute(ManifestAttribute object) {
		addContent(object.getKey());
		addContent(object.getValue());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseManifestEntry(org.eclipse.gmt.modisco.java.ManifestEntry)
	 */
	@Override
	public List<ASTNode> caseManifestEntry(ManifestEntry object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseMethodRefParameter(org.eclipse.gmt.modisco.java.MethodRefParameter)
	 */
	@Override
	public List<ASTNode> caseMethodRefParameter(MethodRefParameter object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseTagElement(org.eclipse.gmt.modisco.java.TagElement)
	 */
	@Override
	public List<ASTNode> caseTagElement(TagElement object) {
		addContent(object.getTagName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseTextElement(org.eclipse.gmt.modisco.java.TextElement)
	 */
	@Override
	public List<ASTNode> caseTextElement(TextElement object) {
		addComment(object.getText());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseAbstractTypeDeclaration(org.eclipse.gmt.modisco.java.AbstractTypeDeclaration)
	 */
	@Override
	public List<ASTNode> caseAbstractTypeDeclaration(
			AbstractTypeDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseAnnotationTypeDeclaration(org.eclipse.gmt.modisco.java.AnnotationTypeDeclaration)
	 */
	@Override
	public List<ASTNode> caseAnnotationTypeDeclaration(
			AnnotationTypeDeclaration object) {
		addComment(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseArrayType(org.eclipse.gmt.modisco.java.ArrayType)
	 */
	@Override
	public List<ASTNode> caseArrayType(ArrayType object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseClassDeclaration(org.eclipse.gmt.modisco.java.ClassDeclaration)
	 */
	@Override
	public List<ASTNode> caseClassDeclaration(ClassDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseEnumDeclaration(org.eclipse.gmt.modisco.java.EnumDeclaration)
	 */
	@Override
	public List<ASTNode> caseEnumDeclaration(EnumDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseInterfaceDeclaration(org.eclipse.gmt.modisco.java.InterfaceDeclaration)
	 */
	@Override
	public List<ASTNode> caseInterfaceDeclaration(InterfaceDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseParameterizedType(org.eclipse.gmt.modisco.java.ParameterizedType)
	 */
	@Override
	public List<ASTNode> caseParameterizedType(ParameterizedType object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#casePrimitiveType(org.eclipse.gmt.modisco.java.PrimitiveType)
	 */
	@Override
	public List<ASTNode> casePrimitiveType(PrimitiveType object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#casePrimitiveTypeBoolean(org.eclipse.gmt.modisco.java.PrimitiveTypeBoolean)
	 */
	@Override
	public List<ASTNode> casePrimitiveTypeBoolean(PrimitiveTypeBoolean object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#casePrimitiveTypeByte(org.eclipse.gmt.modisco.java.PrimitiveTypeByte)
	 */
	@Override
	public List<ASTNode> casePrimitiveTypeByte(PrimitiveTypeByte object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#casePrimitiveTypeChar(org.eclipse.gmt.modisco.java.PrimitiveTypeChar)
	 */
	@Override
	public List<ASTNode> casePrimitiveTypeChar(PrimitiveTypeChar object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#casePrimitiveTypeDouble(org.eclipse.gmt.modisco.java.PrimitiveTypeDouble)
	 */
	@Override
	public List<ASTNode> casePrimitiveTypeDouble(PrimitiveTypeDouble object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#casePrimitiveTypeShort(org.eclipse.gmt.modisco.java.PrimitiveTypeShort)
	 */
	@Override
	public List<ASTNode> casePrimitiveTypeShort(PrimitiveTypeShort object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#casePrimitiveTypeFloat(org.eclipse.gmt.modisco.java.PrimitiveTypeFloat)
	 */
	@Override
	public List<ASTNode> casePrimitiveTypeFloat(PrimitiveTypeFloat object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#casePrimitiveTypeInt(org.eclipse.gmt.modisco.java.PrimitiveTypeInt)
	 */
	@Override
	public List<ASTNode> casePrimitiveTypeInt(PrimitiveTypeInt object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#casePrimitiveTypeLong(org.eclipse.gmt.modisco.java.PrimitiveTypeLong)
	 */
	@Override
	public List<ASTNode> casePrimitiveTypeLong(PrimitiveTypeLong object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#casePrimitiveTypeVoid(org.eclipse.gmt.modisco.java.PrimitiveTypeVoid)
	 */
	@Override
	public List<ASTNode> casePrimitiveTypeVoid(PrimitiveTypeVoid object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseType(org.eclipse.gmt.modisco.java.Type)
	 */
	@Override
	public List<ASTNode> caseType(Type object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseTypeDeclaration(org.eclipse.gmt.modisco.java.TypeDeclaration)
	 */
	@Override
	public List<ASTNode> caseTypeDeclaration(TypeDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseTypeParameter(org.eclipse.gmt.modisco.java.TypeParameter)
	 */
	@Override
	public List<ASTNode> caseTypeParameter(TypeParameter object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseUnresolvedAnnotationDeclaration(org.eclipse.gmt.modisco.java.UnresolvedAnnotationDeclaration)
	 */
	@Override
	public List<ASTNode> caseUnresolvedAnnotationDeclaration(
			UnresolvedAnnotationDeclaration object) {
		addComment(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseUnresolvedClassDeclaration(org.eclipse.gmt.modisco.java.UnresolvedClassDeclaration)
	 */
	@Override
	public List<ASTNode> caseUnresolvedClassDeclaration(
			UnresolvedClassDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseUnresolvedEnumDeclaration(org.eclipse.gmt.modisco.java.UnresolvedEnumDeclaration)
	 */
	@Override
	public List<ASTNode> caseUnresolvedEnumDeclaration(
			UnresolvedEnumDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseUnresolvedInterfaceDeclaration(org.eclipse.gmt.modisco.java.UnresolvedInterfaceDeclaration)
	 */
	@Override
	public List<ASTNode> caseUnresolvedInterfaceDeclaration(
			UnresolvedInterfaceDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseUnresolvedType(org.eclipse.gmt.modisco.java.UnresolvedType)
	 */
	@Override
	public List<ASTNode> caseUnresolvedType(UnresolvedType object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseUnresolvedTypeDeclaration(org.eclipse.gmt.modisco.java.UnresolvedTypeDeclaration)
	 */
	@Override
	public List<ASTNode> caseUnresolvedTypeDeclaration(
			UnresolvedTypeDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseWildCardType(org.eclipse.gmt.modisco.java.WildCardType)
	 */
	@Override
	public List<ASTNode> caseWildCardType(WildCardType object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseAbstractMethodDeclaration(org.eclipse.gmt.modisco.java.AbstractMethodDeclaration)
	 */
	@Override
	public List<ASTNode> caseAbstractMethodDeclaration(
			AbstractMethodDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseAnnotationMemberValuePair(org.eclipse.gmt.modisco.java.AnnotationMemberValuePair)
	 */
	@Override
	public List<ASTNode> caseAnnotationMemberValuePair(
			AnnotationMemberValuePair object) {
		addComment(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseAnnotationTypeMemberDeclaration(org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration)
	 */
	@Override
	public List<ASTNode> caseAnnotationTypeMemberDeclaration(
			AnnotationTypeMemberDeclaration object) {
		addComment(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseBodyDeclaration(org.eclipse.gmt.modisco.java.BodyDeclaration)
	 */
	@Override
	public List<ASTNode> caseBodyDeclaration(BodyDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseConstructorDeclaration(org.eclipse.gmt.modisco.java.ConstructorDeclaration)
	 */
	@Override
	public List<ASTNode> caseConstructorDeclaration(
			ConstructorDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseEnumConstantDeclaration(org.eclipse.gmt.modisco.java.EnumConstantDeclaration)
	 */
	@Override
	public List<ASTNode> caseEnumConstantDeclaration(
			EnumConstantDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseFieldDeclaration(org.eclipse.gmt.modisco.java.FieldDeclaration)
	 */
	@Override
	public List<ASTNode> caseFieldDeclaration(FieldDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseInitializer(org.eclipse.gmt.modisco.java.Initializer)
	 */
	@Override
	public List<ASTNode> caseInitializer(Initializer object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseMethodDeclaration(org.eclipse.gmt.modisco.java.MethodDeclaration)
	 */
	@Override
	public List<ASTNode> caseMethodDeclaration(MethodDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseSingleVariableDeclaration(org.eclipse.gmt.modisco.java.SingleVariableDeclaration)
	 */
	@Override
	public List<ASTNode> caseSingleVariableDeclaration(
			SingleVariableDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseUnresolvedAnnotationTypeMemberDeclaration(org.eclipse.gmt.modisco.java.UnresolvedAnnotationTypeMemberDeclaration)
	 */
	@Override
	public List<ASTNode> caseUnresolvedAnnotationTypeMemberDeclaration(
			UnresolvedAnnotationTypeMemberDeclaration object) {
		addComment(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseUnresolvedMethodDeclaration(org.eclipse.gmt.modisco.java.UnresolvedMethodDeclaration)
	 */
	@Override
	public List<ASTNode> caseUnresolvedMethodDeclaration(
			UnresolvedMethodDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseUnresolvedVariableDeclarationFragment(org.eclipse.gmt.modisco.java.UnresolvedVariableDeclarationFragment)
	 */
	@Override
	public List<ASTNode> caseUnresolvedVariableDeclarationFragment(
			UnresolvedVariableDeclarationFragment object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseUnresolvedSingleVariableDeclaration(org.eclipse.gmt.modisco.java.UnresolvedSingleVariableDeclaration)
	 */
	@Override
	public List<ASTNode> caseUnresolvedSingleVariableDeclaration(
			UnresolvedSingleVariableDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseVariableDeclaration(org.eclipse.gmt.modisco.java.VariableDeclaration)
	 */
	@Override
	public List<ASTNode> caseVariableDeclaration(VariableDeclaration object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseVariableDeclarationFragment(org.eclipse.gmt.modisco.java.VariableDeclarationFragment)
	 */
	@Override
	public List<ASTNode> caseVariableDeclarationFragment(
			VariableDeclarationFragment object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseArchive(org.eclipse.gmt.modisco.java.Archive)
	 */
	@Override
	public List<ASTNode> caseArchive(Archive object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseCharacterLiteral(org.eclipse.gmt.modisco.java.CharacterLiteral)
	 */
	@Override
	public List<ASTNode> caseCharacterLiteral(CharacterLiteral object) {
		addContent('|' + object.getEscapedValue() + '|');
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseClassFile(org.eclipse.gmt.modisco.java.ClassFile)
	 */
	@Override
	public List<ASTNode> caseClassFile(ClassFile object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseNumberLiteral(org.eclipse.gmt.modisco.java.NumberLiteral)
	 */
	@Override
	public List<ASTNode> caseNumberLiteral(NumberLiteral object) {
		addContent(object.getTokenValue());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseStringLiteral(org.eclipse.gmt.modisco.java.StringLiteral)
	 */
	@Override
	public List<ASTNode> caseStringLiteral(StringLiteral object) {
		addContent(object.getEscapedValue());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseLabeledStatement(org.eclipse.gmt.modisco.java.LabeledStatement)
	 */
	@Override
	public List<ASTNode> caseLabeledStatement(LabeledStatement object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.java.emf.util.JavaSwitch#caseUnresolvedLabeledStatement(org.eclipse.gmt.modisco.java.UnresolvedLabeledStatement)
	 */
	@Override
	public List<ASTNode> caseUnresolvedLabeledStatement(
			UnresolvedLabeledStatement object) {
		addContent(object.getName());
		return new ArrayList<ASTNode>();
	}
	
	@Override
	public List<ASTNode> defaultCase(EObject object) {
		// There are ASTNodes that shouldn't be indexed. Their Methods are not implemented.
		// This Method catches all those ASTNodes and ignores them.
		return new ArrayList<ASTNode>();
	}
}
