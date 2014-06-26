
package com.challengercity.datura;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ItemType {
    
    private String name;
    private int id;
    private int meta;
    private int picX;
    private int picY;
    private float weight;
    //private int meta;
    
    public static final ItemType COIN_COPPER = new ItemType().setId(0).setMeta(0).setName(StringHandler.getString("item_coin_copper")).setPicX(0).setPicY(0).setWeight(0.02f);
    public static final ItemType COIN_SILVER = new ItemType().setId(0).setMeta(1).setName(StringHandler.getString("item_coin_silver")).setPicX(0).setPicY(0).setWeight(0.02f);
    public static final ItemType COIN_GOLD = new ItemType().setId(0).setMeta(2).setName(StringHandler.getString("item_coin_gold")).setPicX(0).setPicY(0).setWeight(0.01f);
    
    public static final ItemType ORE = new ItemType().setId(1).setName(StringHandler.getString("item_ore")).setWeight(3.00f); // TODO Add more ore types
    
    public static final ItemType PICKAXE_COPPER = new ItemType().setId(2).setMeta(0).setName(StringHandler.getString("item_pickaxe_copper"));
    public static final ItemType PICKAXE_BRONZE = new ItemType().setId(2).setMeta(1).setName(StringHandler.getString("item_pickaxe_bronze"));
    public static final ItemType PICKAXE_IRON = new ItemType().setId(2).setMeta(2).setName(StringHandler.getString("item_pickaxe_iron"));
    public static final ItemType PICKAXE_TITANIUM = new ItemType().setId(2).setMeta(3).setName(StringHandler.getString("item_pickaxe_titanium"));
    public static final ItemType PICKAXE_TUNGSTEN = new ItemType().setId(2).setMeta(4).setName(StringHandler.getString("item_pickaxe_tungsten"));
    public static final ItemType PICKAXE_ZINC = new ItemType().setId(2).setMeta(5).setName(StringHandler.getString("item_pickaxe_zinc"));
    public static final ItemType PICKAXE_STEEL = new ItemType().setId(2).setMeta(6).setName(StringHandler.getString("item_pickaxe_steel"));
    
    public static final ItemType INGOT_COPPER = new ItemType().setId(3).setMeta(0).setName(StringHandler.getString("item_ingot_copper"));
    public static final ItemType INGOT_BRONZE = new ItemType().setId(3).setMeta(1).setName(StringHandler.getString("item_ingot_bronze"));
    public static final ItemType INGOT_IRON = new ItemType().setId(3).setMeta(2).setName(StringHandler.getString("item_ingot_iron"));
    public static final ItemType INGOT_TITANIUM = new ItemType().setId(3).setMeta(3).setName(StringHandler.getString("item_ingot_titanium"));
    public static final ItemType INGOT_TUNGSTEN = new ItemType().setId(3).setMeta(4).setName(StringHandler.getString("item_ingot_tungsten"));
    public static final ItemType INGOT_ZINC = new ItemType().setId(3).setMeta(5).setName(StringHandler.getString("item_ingot_zinc"));
    public static final ItemType INGOT_STEEL = new ItemType().setId(3).setMeta(6).setName(StringHandler.getString("item_ingot_steel"));
    public static final ItemType INGOT_GOLD = new ItemType().setId(3).setMeta(7).setName(StringHandler.getString("item_ingot_gold"));
    
    public static final ItemType POTION_SPEED = new ItemType().setId(4).setMeta(0).setName(StringHandler.getString("item_potion_speed"));
    public static final ItemType POTION_HEALTH = new ItemType().setId(4).setMeta(1).setName(StringHandler.getString("item_potion_health"));
    public static final ItemType POTION_VISION = new ItemType().setId(4).setMeta(2).setName(StringHandler.getString("item_potion_vision"));
    public static final ItemType POTION_INVIS = new ItemType().setId(4).setMeta(3).setName(StringHandler.getString("item_potion_invisibility"));
    
    public static final ItemType TRAP_PIT = new ItemType().setId(5).setMeta(0).setName(StringHandler.getString("item_trap_pit"));
    public static final ItemType TRAP_BLIND = new ItemType().setId(5).setMeta(1).setName(StringHandler.getString("item_trap_blind"));
    public static final ItemType TRAP_SLOW = new ItemType().setId(5).setMeta(2).setName(StringHandler.getString("item_trap_slow"));
    public static final ItemType TRAP_POISON = new ItemType().setId(5).setMeta(3).setName(StringHandler.getString("item_trap_posion"));
    public static final ItemType TRAP_COLLAPSE = new ItemType().setId(5).setMeta(4).setName(StringHandler.getString("item_trap_collapse"));
    public static final ItemType TRAP_CONFUSE = new ItemType().setId(5).setMeta(5).setName(StringHandler.getString("item_trap_confuse"));
    public static final ItemType TRAP_REVERSE = new ItemType().setId(5).setMeta(6).setName(StringHandler.getString("item_trap_reverse"));
    
    public static final ItemType SWORD_COPPER = new ItemType().setId(6).setMeta(0).setName(StringHandler.getString("item_sword_copper"));
    public static final ItemType SWORD_BRONZE = new ItemType().setId(6).setMeta(1).setName(StringHandler.getString("item_sword_bronze"));
    public static final ItemType SWORD_IRON = new ItemType().setId(6).setMeta(2).setName(StringHandler.getString("item_sword_iron"));
    public static final ItemType SWORD_TITANIUM = new ItemType().setId(6).setMeta(3).setName(StringHandler.getString("item_sword_titanium"));
    public static final ItemType SWORD_TUNGSTEN = new ItemType().setId(6).setMeta(4).setName(StringHandler.getString("item_sword_tungsten"));
    public static final ItemType SWORD_ZINC = new ItemType().setId(6).setMeta(5).setName(StringHandler.getString("item_sword_zinc"));
    public static final ItemType SWORD_STEEL = new ItemType().setId(6).setMeta(6).setName(StringHandler.getString("item_sword_steel"));
    
    public static final ItemType BOW_WOOD = new ItemType().setId(7).setMeta(0).setName(StringHandler.getString("item_bow_wood"));
    public static final ItemType BOW_POLYMER = new ItemType().setId(7).setMeta(1).setName(StringHandler.getString("item_bow_polymer"));
    
    public static final ItemType SLINGSHOT = new ItemType().setId(8).setName(StringHandler.getString("item_slingshot"));
    
    public static final ItemType FURNITURE_CHAIR_BASIC = new ItemType().setId(9).setMeta(0).setName(StringHandler.getString("item_furniture_chair_basic"));
    public static final ItemType FURNITURE_TABLE_BASIC = new ItemType().setId(9).setMeta(1).setName(StringHandler.getString("item_furniture_table_basic"));
    public static final ItemType MACHINE_COMPUTER = new ItemType().setId(9).setMeta(2).setName(StringHandler.getString("item_furniture_machine_computer"));
    public static final ItemType MACINE_PURIFIER = new ItemType().setId(9).setMeta(3).setName(StringHandler.getString("item_furniture_machine_purifier"));
    public static final ItemType MACHINE_TRANSMUTATOR = new ItemType().setId(9).setMeta(4).setName(StringHandler.getString("item_furniture_machine_transmutator"));
    
    public static final ItemType TRACER = new ItemType().setId(10).setName(StringHandler.getString("item_tracer"));
    
//    item_bow_wood=Wood Bow
//    item_bow_polymer=Polymer Bow
//    item_slingshot=Slingshot
//    item_furniture_chair_basic=Basic Chair
//    item_furniture_table_basic=Basic Table
//    item_furniture_machine_computer=Computer
//    item_furniture_machine_purifier=Ore Purifier
//    item_furniture_machine_transmutator=Transmutation Machine
//    item_tracer=Tracer

    public ItemType() {
    }

    public ItemType setId(int id) {
        this.id = id;
        return this;
    }

    public ItemType setName(String name) {
        this.name = name;
        return this;
    }

    public ItemType setPicX(int picX) {
        this.picX = picX;
        return this;
    }

    public ItemType setPicY(int picY) {
        this.picY = picY;
        return this;
    }

    public ItemType setMeta(int meta) {
        this.meta = meta;
        return this;
    }

    public ItemType setWeight(float weight) {
        this.weight = weight;
        return this;
    }

    public float getWeight() {
        return weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPicX() {
        return picX;
    }

    public int getPicY() {
        return picY;
    }

    public int getMeta() {
        return meta;
    }
}
