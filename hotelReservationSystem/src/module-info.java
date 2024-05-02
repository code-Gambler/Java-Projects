module Project {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.base;
	
	opens application to javafx.fxml,javafx.base;
	opens controllers to javafx.fxml,javafx.base;
	opens model to javafx.base;
	
	exports application to javafx.graphics,javafx.fxml;
	exports controllers to javafx.graphics,javafx.fxml;
}
