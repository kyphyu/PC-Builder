interface PC {
    String getDescription();
    double getPrice();
}

abstract class PCDecorator implements PC{
    protected PC pc;
    public PCDecorator(PC pc){
        this.pc = pc;
    }
}

class BasePC implements PC{
    public String getDescription(){
        return "Base PC";
    }
    public double getPrice(){
        return 500.0;
    }
}

class RAMUpgrade extends PCDecorator{
    private int sizeGB;
    private double price;
    public RAMUpgrade(PC pc, int sizeGB){
        super(pc);
        this.sizeGB = sizeGB;
        this.price = calculatePrice(sizeGB);
    }

    private double calculatePrice(int sizeGB) {
        switch (sizeGB) {
            case 8:  return 50.0;
            case 16: return 100.0;
            case 32: return 180.0;
            case 64: return 320.0;
            default: return 100.0;
        }
    }

    public String getDescription() {
        return pc.getDescription() + " + " + sizeGB + "GB RAM";
    }
    public double getPrice() {
        return pc.getPrice() + price;
    }
}

class StorageUpgrade extends PCDecorator {
    private int sizeGB;
    private String type; // "HDD", "SSD", "NVMe"
    private double price;

    public StorageUpgrade(PC pc, int sizeGB, String type) {
        super(pc);
        this.sizeGB = sizeGB;
        this.type = type;
        this.price = calculatePrice(sizeGB, type);
    }

    private double calculatePrice(int sizeGB, String type) {
        double basePerGB;
        switch (type) {
            case "HDD":  basePerGB = 20.0; break;
            case "SSD":  basePerGB = 35.0; break;
            case "NVMe": basePerGB = 50.0; break;
            default:     basePerGB = 10.0;
        }
        return sizeGB * basePerGB;
    }

    public String getDescription() {
        return pc.getDescription() + " + " + sizeGB + "GB " + type;
    }
    public double getPrice() {
        return pc.getPrice() + price;
    }
}
