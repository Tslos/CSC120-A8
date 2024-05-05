public class Item {
    String longDesc;
    String shortDesc;
    boolean limitedUse;
    int numUses;

    /**
     * Constructor for the Item class
     * 
     * @param longDesc   The long, detailed description of the item in question
     * @param shortDesc  The shorter description
     * @param limitedUse If the item is one with limited uses
     * @param numUses    If the item has limited uses, defaults to -1, else fills in
     *                   with the supplied value of use numbers
     */
    public Item(String longDesc, String shortDesc, boolean limitedUse, int numUses) {
        this.longDesc = longDesc;
        this.shortDesc = shortDesc;
        this.limitedUse = limitedUse;
        if (limitedUse) {
            this.numUses = numUses;
        } else {
            this.numUses = -1;
        }

    }

    /**
     * Overloaded, quick Item() constructor
     * 
     * @param desc to be populated both to long and short descriptions
     */
    public Item(String desc) {
        this.longDesc = desc;
        this.shortDesc = desc;
        this.limitedUse = false;
        this.numUses = 1;
    }

    /**
     * Base 'use' function, to be overloaded if this class is extended.
     * 
     * @return the string to be printed out by the user.
     */
    protected String use() {
        String result = "You have used this " + this.shortDesc;
        if (this.limitedUse) {
            this.numUses = this.numUses - 1;
        }
        if (this.numUses == 0) {
            result = this.shortDesc + "has been used up!";
        }
        return (result);
    }
}
