public class PCBuilder {

    public static PC buildPC() {
        PC pc = new BasePC();
        System.out.println("\n=== PC Builder ===");
        System.out.println(pc.getDescription() + " | Base Price: $" + pc.getPrice());

        pc = addRAM(pc);
        pc = addStorage(pc);

        return pc;
    }

    private static PC addRAM(PC pc) {
        System.out.println("\n--- RAM Upgrade ---");
        System.out.println("Available options:");
        System.out.println("  1. 8GB  - $50");
        System.out.println("  2. 16GB - $100");
        System.out.println("  3. 32GB - $180");
        System.out.println("  4. 64GB - $320");

        int choice = InputHelper.getIntInput("Enter choice (1-4): ", 1, 4);

        int[] sizes = {8, 16, 32, 64};
        return new RAMUpgrade(pc, sizes[choice - 1]);
    }

    private static PC addStorage(PC pc) {
        System.out.println("\n--- Storage Upgrade ---");

        // pick type
        System.out.println("Storage type:");
        System.out.println("  1. HDD  - ฿20.0/GB (cheapest)");
        System.out.println("  2. SSD  - ฿35.0/GB (mid range)");
        System.out.println("  3. NVMe - ฿50.0/GB (fastest)");
        int typeChoice = InputHelper.getIntInput("Enter choice (1-3): ", 1, 3);
        String[] types = {"HDD", "SSD", "NVMe"};
        String type = types[typeChoice - 1];

        // pick size
        System.out.println("Storage size:");
        System.out.println("  1. 250GB");
        System.out.println("  2. 500GB");
        System.out.println("  3. 1000GB (1TB)");
        System.out.println("  4. 2000GB (2TB)");
        int sizeChoice = InputHelper.getIntInput("Enter choice (1-4): ", 1, 4);
        int[] sizes = {250, 500, 1000, 2000};
        int size = sizes[sizeChoice - 1];

        return new StorageUpgrade(pc, size, type);
    }
}
