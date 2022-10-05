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
          }
         
    } 
    
    
    

    
}
