public class Item {
    String longDesc;
    String shortDesc;
    boolean limitedUse;
    int numUses;


    public Item(String longDesc, String shortDesc, boolean limitedUse, int numUses) {
        this.longDesc = longDesc;
        this.shortDesc = shortDesc;
        this.limitedUse = limitedUse;
        this.numUses = numUses;
    }
    public Item(String desc) {
        this.longDesc = desc;
        this.shortDesc = desc;
        this.limitedUse = false;
        this.numUses = 1;
    }

    protected String use() {
        String result = "You have used this " + this.shortDesc;
        if (this.limitedUse) {
            this.numUses = this.numUses - 1;
        } 
        if (this.numUses == 0) {
            result = this.shortDesc + "has been used up!";
        }
        return(result);
    }
}
