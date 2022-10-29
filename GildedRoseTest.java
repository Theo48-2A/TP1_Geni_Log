package com.gildedrose;

import org.junit.jupiter.api.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.concurrent.Callable;




class GildedRoseTest {



  @Test
  @DisplayName("Test that the name is unchanged")
  void testName() {
    Item element = new Item("FIXME", 0, 0);
    GildedRose app = new GildedRose(new Item[] {element});
    //app.updateQuality();
    app.newUpdateQuality();
    assertThat(app.items[0].name, is("FIXME")); //app.items[0] est en faite l'item element

    
  }
  
// La qualité (`quality`) d'un produit ne peut jamais être négative.  
// La qualité d'un produit n'est jamais de plus de 50.

// L'item s'appelle Aged Brie :

// "Aged Brie" augmente sa qualité (`quality`) plus le temps passe.

// En faite pour AB, si la péremption est <= 0 la qualité prend +1 sinon elle prend +2, sachant qu'on ne peut pas dépasser 50, si la qualité est déjà au max on augmente plus.

//Qualité == 50

  //peremption <= 0
  @Test
  void testAgedBrieUn() {
  
    int peremption = 0;
    int qualite = 50;
    Item element = new Item("Aged Brie", peremption, qualite);
    GildedRose app = new GildedRose(new Item[] {element});
    //app.updateQuality();
    app.newUpdateQuality();
    assertThat(element.name, is("Aged Brie"));
    assertThat(element.sellIn, is(peremption-1));
    assertThat(element.quality, is(qualite));
    
  }
// Qualité < 49:
  //peremption > 0
  @Test
  void testAgedBrieDeux() {
  
    int peremption = 10;
    int qualite = 48;
    Item element = new Item("Aged Brie", peremption, qualite);
    GildedRose app = new GildedRose(new Item[] {element});
    //app.updateQuality();
    app.newUpdateQuality();
    assertThat(element.name, is("Aged Brie"));
    assertThat(element.sellIn, is(peremption-1));
    assertThat(element.quality, is(qualite+1));
    
  }
  //peremption <= 0
  @Test
  void testAgedBrieTrois() {
  
    int peremption = 0;
    int qualite = 48;
    Item element = new Item("Aged Brie", peremption, qualite);
    GildedRose app = new GildedRose(new Item[] {element});
    //app.updateQuality();
    app.newUpdateQuality();
    assertThat(element.name, is("Aged Brie"));
    assertThat(element.sellIn, is(peremption-1));
    assertThat(element.quality, is(qualite+2));
    
  }
  
// L'item s'appelle Sulfuras, Hand of Ragnaros :
//"Sulfuras", étant un objet légendaire, n'a pas de date de péremption et ne perd jamais en qualité (`quality`)
//"Sulfuras" est un objet légendaire et comme tel sa qualité est de 80 et il ne change jamais.

// peremption < 0
// Sulfuras ne périme pas donc le cas peremption < 0 ne devrait pas avoir à etre traité, cependant pour obtenir 100 pourcent sur Jacoco et donc passer par toutes les eventualités possible, ce test est nécessaire

@Test
  void testSulfurasHandOfRagnarosUn() {
  
    int peremption = -1;
    int qualite = 80;
    Item element = new Item("Sulfuras, Hand of Ragnaros", peremption, qualite);
    GildedRose app = new GildedRose(new Item[] {element});
    //app.updateQuality();
    app.newUpdateQuality();
    assertThat(element.name, is("Sulfuras, Hand of Ragnaros"));
    assertThat(element.sellIn, is(peremption));
    assertThat(element.quality, is(qualite));
    
  }

// peremption >= 0

@Test
  void testSulfurasHandOfRagnarosDeux() {
  
    int peremption = 1;
    int qualite = 80;
    Item element = new Item("Sulfuras, Hand of Ragnaros", peremption, qualite);
    GildedRose app = new GildedRose(new Item[] {element});
    //app.updateQuality();
    app.newUpdateQuality();
    assertThat(element.name, is("Sulfuras, Hand of Ragnaros"));
    assertThat(element.sellIn, is(peremption));
    assertThat(element.quality, is(qualite));
    
  }


// L'item s'appelle Backstage passes to a TAFKAL80ETC concert :
/* "Backstage passes", comme le "Aged Brie", augmente sa qualité (`quality`) plus le temps passe (`sellIn`) ; La qualité augmente de 2 quand il reste 10 jours ou moins et de 3 quand il reste 5 jours ou moins, mais la qualité tombe à 0 après le concert.*/

// peremption >= 11
  // Qualité < 50
  
  @Test
  void testBackstagePassesToATafkal80EtcConcertUn() {
  
    int peremption = 11;
    int qualite = 49;
    Item element = new Item("Backstage passes to a TAFKAL80ETC concert", peremption, qualite);
    GildedRose app = new GildedRose(new Item[] {element});
    //app.updateQuality();
    app.newUpdateQuality();
    assertThat(element.name, is("Backstage passes to a TAFKAL80ETC concert"));
    assertThat(element.sellIn, is(peremption-1));
    assertThat(element.quality, is(qualite+1));
    
  }
  
  
// 6 < peremption < 11
//La qualité augmente de 2 quand il reste 10 jours ou moins

  // Qualité < 50
  
  @Test
  void testBackstagePassesToATafkal80EtcConcertDeux() {
  
    int peremption = 10;
    int qualite = 48;
    Item element = new Item("Backstage passes to a TAFKAL80ETC concert", peremption, qualite);
    GildedRose app = new GildedRose(new Item[] {element});
    //app.updateQuality();
    app.newUpdateQuality();
    assertThat(element.name, is("Backstage passes to a TAFKAL80ETC concert"));
    assertThat(element.sellIn, is(peremption-1));
    assertThat(element.quality, is(qualite+2));
    
  }
  




// peremption <6


//et de 3 quand il reste 5 jours ou moins,


  // Qualité < 48
  
  @Test
  void testBackstagePassesToATafkal80EtcConcertTrois() {
  
    int peremption = 5;
    int qualite = 47;
    Item element = new Item("Backstage passes to a TAFKAL80ETC concert", peremption, qualite);
    GildedRose app = new GildedRose(new Item[] {element});
    //app.updateQuality();
    app.newUpdateQuality();
    assertThat(element.name, is("Backstage passes to a TAFKAL80ETC concert"));
    assertThat(element.sellIn, is(peremption-1));
    assertThat(element.quality, is(qualite+3));
    
  }
  
   
  
  
  // Qualité == 49
  @Test
  void testBackstagePassesToATafkal80EtcConcertQuattre() {
  
    int peremption = 5;
    int qualite = 49;
    Item element = new Item("Backstage passes to a TAFKAL80ETC concert", peremption, qualite);
    GildedRose app = new GildedRose(new Item[] {element});
    //app.updateQuality();
    app.newUpdateQuality();
    assertThat(element.name, is("Backstage passes to a TAFKAL80ETC concert"));
    assertThat(element.sellIn, is(peremption-1));
    assertThat(element.quality, is(qualite+1));
    
  }





//peremption <= 0
// mais la qualité tombe à 0 après le concert.
  @Test
  void testBackstagePassesToATafkal80EtcConcertCinq() {
  
    int peremption = 0;
    int qualite = 40;
    Item element = new Item("Backstage passes to a TAFKAL80ETC concert", peremption, qualite);
    GildedRose app = new GildedRose(new Item[] {element});
    //app.updateQuality();
    app.newUpdateQuality();
    assertThat(element.name, is("Backstage passes to a TAFKAL80ETC concert"));
    assertThat(element.sellIn, is(peremption-1));
    assertThat(element.quality, is(0));
    
  }
  
 


// Si on crée un item qui ne s'appelle pas AB, ni BPTC ni SHR, 

// 0 <= qualite < 50 

//Une fois que la date de péremption est passée, la qualité se dégrade deux fois plus rapidement.
//péremption <= 0

  @Test
  void testQuelconqueUn() {
    int peremption = 0;
    int qualite = 2;
    Item element = new Item("NotreItem", peremption, qualite);
    GildedRose app = new GildedRose(new Item[] {element});
    //app.updateQuality();
    app.newUpdateQuality();
    assertThat(element.name, is("NotreItem"));
    assertThat(element.sellIn, is(peremption-1));
    assertThat(element.quality, is(qualite-2));
    
  }
 

//péremption > 0
  @Test
  void testQuelconqueDeux() {
    int peremption = 5;
    int qualite = 10;
    Item element = new Item("NotreItem", peremption, qualite);
    GildedRose app = new GildedRose(new Item[] {element});
    //app.updateQuality();
    app.newUpdateQuality();
    assertThat(element.name, is("NotreItem"));
    assertThat(element.sellIn, is(peremption-1));
    assertThat(element.quality, is(qualite-1));
    
  }



//Test Conjured
// Conjured perd 4 points de qualité si sellIn est négatif et 2 points si sellIn est positif
//péremption <= 0

  @Test
  void testConjuredUn() {
    int peremption = 0;
    int qualite = 10;
    Item element = new Item("Conjured", peremption, qualite);
    GildedRose app = new GildedRose(new Item[] {element});
    //app.updateQuality();
    app.newUpdateQuality();
    assertThat(element.name, is("Conjured"));
    assertThat(element.sellIn, is(peremption-1));
    assertThat(element.quality, is(qualite-4));
    
  }
 

//péremption >= 1
  @Test
  void testConjuredDeux() {
    int peremption = 1;
    int qualite = 10;
    Item element = new Item("Conjured", peremption, qualite);
    GildedRose app = new GildedRose(new Item[] {element});
    //app.updateQuality();
    app.newUpdateQuality();
    assertThat(element.name, is("Conjured"));
    assertThat(element.sellIn, is(peremption-1));
    assertThat(element.quality, is(qualite-2));
    
  }
 
    @Test
    void testExeptions() {
    
       int peremption = 1;
       int qualite = 55;
       Item element = new Item("Conjured", peremption, qualite);
       GildedRose app = new GildedRose(new Item[] {element});

       Assertions.assertThrows(IllegalArgumentException.class, () -> {
            app.newUpdateQuality();
       });
       
       qualite = -10;
       
       Assertions.assertThrows(IllegalArgumentException.class, () -> {
            app.newUpdateQuality();
       });
       
       qualite = 20;
       Item elementt = new Item("Sulfuras, Hand of Ragnaros", peremption, qualite);
       GildedRose appp = new GildedRose(new Item[] {element});
       
       Assertions.assertThrows(IllegalArgumentException.class, () -> {
            appp.newUpdateQuality();
       });
       
    }
  
}
