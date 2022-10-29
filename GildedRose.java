package com.gildedrose;

class GildedRose {
    protected Item[] items;

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
    
    


    public void newUpdateQuality() {

        for (Item item : this.items){  // On parcourt les items de l'objet
        
          if(item.quality < 0){
             throw new IllegalArgumentException("La qualité ne peut pas être négative");     // Si la qualité est négative on déclenche une exeption
          }
          else if(item.quality > 50 && !item.name.equals("Sulfuras, Hand of Ragnaros")){
             throw new IllegalArgumentException("La qualité ne peut pas dépasser 50");      // Si la qualité est supérieur à 50 on déclenche une exeption
          }
          
          
          if(!item.name.equals("Sulfuras, Hand of Ragnaros")){   //Tous les autres perdent 1 point sauf Sulfuras, qui perd aucun points
             item.sellIn -= 1;
          }
          

        
          switch(item.name){	//maintenant on a 4 possibilités
   
             case "Sulfuras, Hand of Ragnaros": //L'item est un "Sulfuras, Hand of Ragnaros"
                //Qualité doit tjr être égal à 80, si c'est pas le cas on retourne une exeption
                if(item.quality != 80){
                   throw new IllegalArgumentException("La qualité de Sulfuras, Hand of Ragnaros est obligatoirement de 80");
                }
                break;
                
             case "Aged Brie":   //L'item est un "Aged Brie"
                item = agedBrie(item);
                break;
                
             case "Backstage passes to a TAFKAL80ETC concert":  //L'item est un "Backstage passes to a TAFKAL80ETC concert"
                item = backstagePassesToATafkal80EtcConcertTrois(item);
                break;
             
             case "Conjured": //L'item est un Conjured
                item = conjured(item);
                break;

             default:  //L'item porte un nom quelconque 
                item = defaultItem(item);
                break;
          }

        }
    } 
    
    
    
    private Item agedBrie(Item item){
        if(item.sellIn < 0){
           item.quality += 2;
        }
        else{
           item.quality += 1;
        }
        item = testQualityBorneSup(item, 50); // La qualité ne peut pas dépasser 50
        return item;
          
    }
    
    private Item backstagePassesToATafkal80EtcConcertTrois(Item item){
        if(item.sellIn < 0){
           item.quality = 0;
        }
        else if(item.sellIn < 6){
           item.quality += 3;
        }
        else if(item.sellIn < 11){
           item.quality += 2;
        }
        else{
           item.quality += 1;
        }
        item = testQualityBorneSup(item, 50); // La qualité ne peut pas dépasser 50
        return item;
          
    }
    
    private Item conjured(Item item){
        if(item.sellIn < 0){
           item.quality -= 4;
        }
        else{
           item.quality -= 2;
        }
        item = testQualityBorneInf(item, 0); // La qualité ne peut pas descendre en dessous 0
        return item;
          
    }
    
    private Item defaultItem(Item item){
        if(item.sellIn < 0){
           item.quality -= 2;
        }
        else{
           item.quality -= 1;
        }
        item = testQualityBorneInf(item, 0); // La qualité ne peut pas descendre en dessous 0
        return item;
          
    }
    
    private Item testQualityBorneSup(Item item, int borne){
        if(item.quality > borne){  // La qualité ne peut pas dépasser borne
           item.quality = borne;
        }
        return item;
    }
    
    private Item testQualityBorneInf(Item item, int borne){
        if(item.quality < borne){  // La qualité ne peut pas descendre en dessous de borne
           item.quality = borne;
        }
        return item;
    
    }
}
