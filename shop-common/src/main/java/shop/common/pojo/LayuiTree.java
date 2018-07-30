package shop.common.pojo;

import java.util.List;

public class LayuiTree {

	private String name;
	private Boolean spread;
	private String href;
	
	private List<LayuiTree> children;
	private String alias;
	private Long id;
	
	private Long parentId;
	
	
	
	/*public LayuiTree(String name, Long id, Long parentId) {
		this.name = name;
		this.id = id;
		this.parentId = parentId;
	}
	
	public LayuiTree(String name, Long id, LayuiTree parent) {
		this.name = name;
		this.id = id;
		this.parentId = parent.getId();
	}*/
	
	
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getSpread() {
		return spread;
	}
	public void setSpread(Boolean spread) {
		this.spread = spread;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public List<LayuiTree> getChildren() {
		return children;
	}
	public void setChildren(List<LayuiTree> children) {
		this.children = children;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
