// 3. Background Job Execution
// Execute tasks asynchronously using Runnable functional interface

public class BackgroundJobExecution {
    public static void main(String[] args) {
        System.out.println("Main Thread: Starting background jobs...\n");
        
        // Job 1: Data Processing
        Runnable dataProcessingJob = () -> {
            System.out.println("Job 1 [Data Processing]: Started");
            try {
                for (int i = 1; i <= 5; i++) {
                    Thread.sleep(500);
                    System.out.println("Job 1: Processing data chunk " + i + "/5");
                }
                System.out.println("Job 1 [Data Processing]: Completed ✓\n");
            } catch (InterruptedException e) {
                System.err.println("Job 1 interrupted: " + e.getMessage());
            }
        };
        
        // Job 2: Email Notification
        Runnable emailNotificationJob = () -> {
            System.out.println("Job 2 [Email Notification]: Started");
            try {
                Thread.sleep(800);
                System.out.println("Job 2: Composing email...");
                Thread.sleep(800);
                System.out.println("Job 2: Sending email...");
                Thread.sleep(800);
                System.out.println("Job 2 [Email Notification]: Completed ✓\n");
            } catch (InterruptedException e) {
                System.err.println("Job 2 interrupted: " + e.getMessage());
            }
        };
        
        // Job 3: Database Backup
        Runnable databaseBackupJob = () -> {
            System.out.println("Job 3 [Database Backup]: Started");
            try {
                Thread.sleep(1000);
                System.out.println("Job 3: Backing up database...");
                Thread.sleep(1000);
                System.out.println("Job 3: Verifying backup...");
                Thread.sleep(500);
                System.out.println("Job 3 [Database Backup]: Completed ✓\n");
            } catch (InterruptedException e) {
                System.err.println("Job 3 interrupted: " + e.getMessage());
            }
        };
        
        // Job 4: Simple task using lambda
        Runnable simpleJob = () -> {
            System.out.println("Job 4 [Simple Task]: Executed immediately ✓\n");
        };
        
        // Execute jobs in background threads
        Thread thread1 = new Thread(dataProcessingJob);
        Thread thread2 = new Thread(emailNotificationJob);
        Thread thread3 = new Thread(databaseBackupJob);
        Thread thread4 = new Thread(simpleJob);
        
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        
        System.out.println("Main Thread: All jobs dispatched, continuing main execution...");
        
        // Wait for all threads to complete
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            System.out.println("Main Thread: All background jobs completed successfully!");
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted: " + e.getMessage());
        }
    }
}
