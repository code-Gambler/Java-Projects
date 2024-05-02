module inventoryManagementSystem {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.fxml, javafx.graphics;
	opens model to javafx.base;
}
