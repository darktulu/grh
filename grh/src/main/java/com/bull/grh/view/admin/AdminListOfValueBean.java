package com.bull.grh.view.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.primefaces.component.api.UITree;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bull.grh.service.admin.AdminValueService;
import com.bull.grh.view.admin.vo.TypeVO;
import com.bull.grh.view.admin.vo.ValueVO;

@Component
@Scope("view")
public class AdminListOfValueBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private TreeNode root;
    private TreeNode rootValue;
    private TypeVO selectedType = new TypeVO();
    private TypeVO addedType = new TypeVO();
    private ValueVO selectedValue = new ValueVO();
    private ValueVO addedValue = new ValueVO();
    private List<TypeVO> types;
    private List<ValueVO> parentvalues;
    private String nomType;
    private String nomValue;
    private boolean showValueTree;

    transient private UITree componentType;
    transient HtmlSelectOneMenu htmlSelectOneMenu;

    @Inject
    private transient AdminValueService adminValueService;

    public String add() {
	System.out.println("eeeeee");
	return null;
    }

    public void validateType(FacesContext context, UIComponent component, Object object) throws ValidatorException {
	TypeVO type = (TypeVO) object;
	if (type == null) {
	    throw new ValidatorException(new FacesMessage("Type obligatoire"));
	}
    }

    public void validateParent(FacesContext context, UIComponent component, Object object) throws ValidatorException {
	ValueVO parent = (ValueVO) object;
	if (parent == null && parentvalues.size() > 1) {
	    throw new ValidatorException(new FacesMessage("Parent obligatoire"));
	}
    }

    public String capitalize(String str) {
	if (str.length() == 0)
	    return str;
	return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public void treeBuilderValue(ValueVO value, TreeNode parent) {
	TreeNode treeNode = new DefaultTreeNode(value, parent);
	// if (value.getParent() != null) {
	List<ValueVO> children = getAdminValueService().loadValuesChildren(value.getValue());
	for (ValueVO child : children) {
	    treeBuilderValue(child, treeNode);
	}
	// }
    }

    public void treeBuilder(TypeVO type, TreeNode parent) {

	TreeNode treeNode = new DefaultTreeNode(type, parent);
	// if (type.getParent() != null) {
	List<TypeVO> children = getAdminValueService().loadTypeChildren(type.getNom());

	for (TypeVO child : children) {
	    treeBuilder(child, treeNode);
	}
	// }
    }

    public void buildTypeTree() {
	root = new DefaultTreeNode("root", null);
	List<TypeVO> typeValues = getAdminValueService().loadTypesRoot();
	for (TypeVO typeValue : typeValues) {
	    treeBuilder(typeValue, root);
	}
    }

    public void buildValueTree() {
	rootValue = new DefaultTreeNode("rootValue", null);
	List<ValueVO> valueList = getAdminValueService().loadValuesByDefName(selectedType.getNom());
	if (valueList.size() > 0) {
	    for (ValueVO type : valueList) {
		treeBuilderValue(type, rootValue);
	    }
	    showValueTree = true;
	} else
	    showValueTree = false;
    }

    @PostConstruct
    public void init() {
	buildTypeTree();
    }

    public void showSelectedType() {
	selectedType = (TypeVO) componentType.getRowNode().getData();
	buildValueTree();
    }

    public void updateSelectedType() {
	FacesContext context = FacesContext.getCurrentInstance();

	if (getAdminValueService().typeExists(addedType))
	    context.addMessage(null, new FacesMessage("Type déjà existant !"));

	else {
	    getAdminValueService().updateType(selectedType);
	    buildTypeTree();
	}
	selectedType = new TypeVO();
    }

    public void updateSelectedValue() {

	FacesContext context = FacesContext.getCurrentInstance();

	if (nomValue.length() > 25)
	    context.addMessage(null, new FacesMessage("Invalid value !"));
	else if (nomValue.equals(""))
	    context.addMessage(null, new FacesMessage("Value required !"));
	else if (getAdminValueService().valueExists(selectedValue))
	    context.addMessage(null, new FacesMessage("Value already exists !"));

	else {

	    getAdminValueService().updateValue(selectedValue);
	    buildValueTree();
	}
	selectedValue = new ValueVO();
    }

    public void deleteSelectedType() {
	FacesContext context = FacesContext.getCurrentInstance();
	if (getAdminValueService().loadTypeChildren(selectedType.getNom()).size() == 0) {

	    getAdminValueService().deleteType(selectedType);
	    types = getAdminValueService().loadTypes();
	    buildTypeTree();
	    buildValueTree();

	} else
	    context.addMessage(null, new FacesMessage("Le type ne peut pas être supprimé, supprimez dabord ses fils !"));
	selectedType = new TypeVO();
    }

    public void remplirParent() {
	TypeVO value = (TypeVO) htmlSelectOneMenu.getValue();
	if (value.getParent() != null)
	    parentvalues = getAdminValueService().loadValuesByDefName(value.getParent().getNom());
    }

    public void deleteSelectedValue() {
	FacesContext context = FacesContext.getCurrentInstance();

	if (getAdminValueService().loadValuesChildren(selectedValue.getValue()).size() == 0) {
	    getAdminValueService().deleteValue(selectedValue);
	    buildValueTree();
	} else
	    context.addMessage(null, new FacesMessage(
		    "La valeur ne peut pas être supprimée, supprimez dabord ses fils !"));
	selectedValue = new ValueVO();
    }

    public void ajouterType() {
	FacesContext context = FacesContext.getCurrentInstance();

	if (adminValueService.typeExists(addedType))
	    context.addMessage(null, new FacesMessage("Type déjà existant !"));

	else {
	    adminValueService.save(addedType);
	    types = adminValueService.loadTypes();
	    buildTypeTree();
	    buildValueTree();
	}
	addedType = new TypeVO();
	addedValue = new ValueVO();
	selectedType = new TypeVO();
	selectedValue = new ValueVO();
    }

    public void ajouterValue() {
	FacesContext context = FacesContext.getCurrentInstance();

	if (getAdminValueService().valueExists(addedValue))
	    context.addMessage(null, new FacesMessage("Valeur déjà existante !"));

	else {
	    getAdminValueService().addValue(addedValue);
	    types = getAdminValueService().loadTypes();
	    buildValueTree();

	}
	addedType = new TypeVO();
	addedValue = new ValueVO();
	selectedValue = new ValueVO();
    }

    public void remplirParent(AjaxBehaviorEvent e) {
	TypeVO value = (TypeVO) htmlSelectOneMenu.getValue();
	if (value.getParent() != null)
	    parentvalues = getAdminValueService().loadValuesByDefName(value.getParent().getNom());
    }

    public TreeNode getRoot() {
	return root;
    }

    public void setRoot(TreeNode root) {
	this.root = root;
    }

    public TreeNode getRootValue() {
	return rootValue;
    }

    public void setRootValue(TreeNode rootValue) {
	this.rootValue = rootValue;
    }

    public TypeVO getSelectedType() {
	return selectedType;
    }

    public void setSelectedType(TypeVO selectedType) {
	this.selectedType = selectedType;
    }

    public TypeVO getAddedType() {
	return addedType;
    }

    public void setAddedType(TypeVO addedType) {
	this.addedType = addedType;
    }

    public ValueVO getSelectedValue() {
	return selectedValue;
    }

    public void setSelectedValue(ValueVO selectedValue) {
	this.selectedValue = selectedValue;
    }

    public ValueVO getAddedValue() {
	return addedValue;
    }

    public void setAddedValue(ValueVO addedValue) {
	this.addedValue = addedValue;
    }

    public List<TypeVO> getTypes() {
	if(types == null || types.isEmpty()) types = adminValueService.loadTypes();
	return types;
    }

    public void setTypes(List<TypeVO> types) {
	this.types = types;
    }

    public List<ValueVO> getParentvalues() {
	return parentvalues;
    }

    public void setParentvalues(List<ValueVO> parentvalues) {
	this.parentvalues = parentvalues;
    }

    public String getNomType() {
	return nomType;
    }

    public void setNomType(String nomType) {
	this.nomType = nomType;
    }

    public String getNomValue() {
	return nomValue;
    }

    public void setNomValue(String nomValue) {
	this.nomValue = nomValue;
    }

    public boolean isShowValueTree() {
	return showValueTree;
    }

    public void setShowValueTree(boolean showValueTree) {
	this.showValueTree = showValueTree;
    }

    public UITree getComponentType() {
	return componentType;
    }

    public void setComponentType(UITree componentType) {
	this.componentType = componentType;
    }

    public HtmlSelectOneMenu getHtmlSelectOneMenu() {
	return htmlSelectOneMenu;
    }

    public void setHtmlSelectOneMenu(HtmlSelectOneMenu htmlSelectOneMenu) {
	this.htmlSelectOneMenu = htmlSelectOneMenu;
    }

    public AdminValueService getAdminValueService() {
	return adminValueService;
    }

    public void setAdminValueService(AdminValueService adminValueService) {
	this.adminValueService = adminValueService;
    }

}
