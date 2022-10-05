package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie") && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } 
            else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } 
                    else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } 
                else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }

    }
    
    


    public void New_updateQuality() {

        for (Item item : this.items){  // On parcourt les items de l'objet
        
          if(item.quality < 0){
             throw new IllegalArgumentException("La qualité ne peut pas être négative");     // Si la qualité est négative on déclenche une exeption
          }
          else if(item.quality > 50 && !item.name.equals("Sulfuras, Hand of Ragnaros")){
             throw new IllegalArgumentException("La qualité ne peut pas dépasser 50");      // Si la qualité est supérieur à 50 on déclenche une exeption
          }
          
         if(item.name.equals("Conjured")){	//Conjured perd 2 points de sellIn
             item.sellIn = item.sellIn - 2;
          }
          else if(!item.name.equals("Sulfuras, Hand of Ragnaros")){   //Tous les autres perdent 1 point sauf Sulfuras, qui perd aucun points
             item.sellIn = item.sellIn - 1;
          }
          switch(item.name){	//maintenant on a 4 possibilités
          
             case "Sulfuras, Hand of Ragnaros": //L'item est un "Sulfuras, Hand of Ragnaros"
                //Qualité doit tjr être égal à 80, si c'est pas le cas on retourne une exeption
                if(item.quality != 80){
                   throw new IllegalArgumentException("La qualité de Sulfuras, Hand of Ragnaros est obligatoirement de 80");
                }
                break;
                
             case "Aged Brie":   //L'item est un "Aged Brie"
                item = Aged_Brie(item);
                break;  
                
             case "Backstage passes to a TAFKAL80ETC concert":  //L'item est un "Backstage passes to a TAFKAL80ETC concert"
                item = Backstage_passes_to_a_TAFKAL80ETC_concert(item);
                break;
                
             default:  //L'item porte un nom quelconque ou bien c'est un "Conjured"
                item = default_item(item);
                break;
             
          }
          
          private Item Aged_Brie(Item item){
             if(item.sellIn < 0){
                item.quality = item.quality + 2;
             }
             else{
                item.quality = item.quality + 1;
             }
             item = Test_Quality_borne_sup(item, 50); // La qualité ne peut pas dépasser 50
             return item;
          
          }
          
          private Item Backstage_passes_to_a_TAFKAL80ETC_concert(Item item){
             if(item.sellIn < 0){
                item.quality = 0;
             }
             else if(item.sellIn < 6){
                item.quality = item.quality + 3;
             }
             else if(item.sellIn < 11){
                item.quality = item.quality + 2;
             }
             else{
                item.quality = item.quality + 1;
             }
             item = Test_Quality_borne_sup(item, 50); // La qualité ne peut pas dépasser 50
             return item;
          
          }
    
          private Item default_item(Item item){
             if(item.sellIn < 0){
                item.quality = item.quality - 2;
             }
             else{
                item.quality = item.quality - 1;
             }
             item = Test_Quality_borne_inf(item, 0); // La qualité ne peut pas descendre en dessous 0
             return item;
          
          }
          
          private Item Test_Quality_borne_sup(Item item, int borne){
             if(item.quality > borne){  // La qualité ne peut pas dépasser borne
                item.quality = borne;
             }
             return item;
          }
    
          private Item Test_Quality_borne_inf(Item item, int borne){
             if(item.quality < borne){  // La qualité ne peut pas descendre en dessous de borne
                item.quality = borne;
             }
             return item;
    
          }
         
    } 
    
    
    

    
}
