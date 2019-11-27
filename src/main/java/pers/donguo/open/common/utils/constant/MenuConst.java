package pers.donguo.open.common.utils.constant;

/**
 * <p>Title: MenuConst.java </p>
 * <p>Description: 菜单常量类【包含目录，菜单，按钮】</p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
public class MenuConst {
	/**
	 * 根菜单ID 即所有顶级Menu 的ParentId
	 */
	public static final Long ROOT_MENU_ID = 0L;

	public enum Type {
		/**
		 * Catalog菜单目录
		 */
		CATALOG(0, "Catalog"),
		/**
		 * Menu菜单
		 */
		MENU(1, "Menu"),
		/**
		 * Button按钮
		 */
		BUTTON(2, "Button");

		private Integer value;
		private String description;

		Type(int value, String description) {
			this.value = value;
			this.description = description;
		}

		public int getValue() {
			return this.value;
		}

		public String getDescription() {
			return this.description;
		}
	}
}
