// 2. Data Export Feature
// A reporting module can export in CSV and PDF. Later, JSON support was added.
// Add a default method exportToJSON() to avoid code changes in all implementers

interface DataExporter {
    void exportToCSV(String data);
    void exportToPDF(String data);
    
    // Default method added later for JSON export
    default void exportToJSON(String data) {
        System.out.println("Exporting to JSON format:");
        System.out.println("{");
        System.out.println("  \"data\": \"" + data + "\",");
        System.out.println("  \"format\": \"JSON\",");
        System.out.println("  \"timestamp\": \"" + System.currentTimeMillis() + "\"");
        System.out.println("}");
        System.out.println("JSON export completed!");
    }
}

class SalesReportExporter implements DataExporter {
    @Override
    public void exportToCSV(String data) {
        System.out.println("Sales Report - CSV Export:");
        System.out.println("ProductName,Quantity,Revenue");
        System.out.println(data);
        System.out.println("CSV export completed!");
    }
    
    @Override
    public void exportToPDF(String data) {
        System.out.println("Sales Report - PDF Export:");
        System.out.println("Generating PDF with data: " + data);
        System.out.println("PDF export completed!");
    }
    
    // Uses default JSON export
}

class InventoryReportExporter implements DataExporter {
    @Override
    public void exportToCSV(String data) {
        System.out.println("Inventory Report - CSV Export:");
        System.out.println("ItemID,ItemName,Stock");
        System.out.println(data);
        System.out.println("CSV export completed!");
    }
    
    @Override
    public void exportToPDF(String data) {
        System.out.println("Inventory Report - PDF Export:");
        System.out.println("Creating PDF document with: " + data);
        System.out.println("PDF export completed!");
    }
    
    // Overrides default JSON with custom implementation
    @Override
    public void exportToJSON(String data) {
        System.out.println("Inventory Report - Custom JSON Export:");
        System.out.println("{");
        System.out.println("  \"reportType\": \"Inventory\",");
        System.out.println("  \"data\": \"" + data + "\",");
        System.out.println("  \"warehouse\": \"Main Warehouse\",");
        System.out.println("  \"format\": \"JSON\",");
        System.out.println("  \"generatedBy\": \"InventorySystem\"");
        System.out.println("}");
        System.out.println("Custom JSON export completed!");
    }
}

class CustomerReportExporter implements DataExporter {
    @Override
    public void exportToCSV(String data) {
        System.out.println("Customer Report - CSV Export:");
        System.out.println("CustomerID,Name,TotalPurchases");
        System.out.println(data);
        System.out.println("CSV export completed!");
    }
    
    @Override
    public void exportToPDF(String data) {
        System.out.println("Customer Report - PDF Export:");
        System.out.println("Generating customer report PDF: " + data);
        System.out.println("PDF export completed!");
    }
    
    // Uses default JSON export
}

public class DataExportFeature {
    public static void main(String[] args) {
        System.out.println("=== Reporting Module - Data Export System ===\n");
        
        DataExporter salesExporter = new SalesReportExporter();
        DataExporter inventoryExporter = new InventoryReportExporter();
        DataExporter customerExporter = new CustomerReportExporter();
        
        String salesData = "Laptop,50,$75000";
        String inventoryData = "IT001,Keyboard,500";
        String customerData = "C001,John Doe,$5000";
        
        // Sales Report Export
        System.out.println("1. SALES REPORT EXPORTS:");
        System.out.println("=".repeat(60));
        salesExporter.exportToCSV(salesData);
        System.out.println();
        salesExporter.exportToPDF(salesData);
        System.out.println();
        salesExporter.exportToJSON(salesData); // Uses default method
        
        // Inventory Report Export
        System.out.println("\n2. INVENTORY REPORT EXPORTS:");
        System.out.println("=".repeat(60));
        inventoryExporter.exportToCSV(inventoryData);
        System.out.println();
        inventoryExporter.exportToPDF(inventoryData);
        System.out.println();
        inventoryExporter.exportToJSON(inventoryData); // Uses custom override
        
        // Customer Report Export
        System.out.println("\n3. CUSTOMER REPORT EXPORTS:");
        System.out.println("=".repeat(60));
        customerExporter.exportToCSV(customerData);
        System.out.println();
        customerExporter.exportToPDF(customerData);
        System.out.println();
        customerExporter.exportToJSON(customerData); // Uses default method
    }
}
