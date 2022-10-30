package org.selenium.pom.objects;

import org.selenium.pom.utils.JacksonUtils;

import java.io.IOException;

public class Product {

    private int id; // ovo su fields ili varijable
    private String name; // mora bit isto ime i tip kao u json fajlu

    //default konstruktor, kad radimo sa jackson uvijek je dobro kreirat default konstruktor da nema problema sa realizacijom
    public Product(){}

    //product id-argument
    //na osnovu ajdija koji dodajemo u konstruktor trebamo postaviti product name i id za product object
    public Product(int id) throws IOException {
        Product[] products = JacksonUtils.deserializeJson("products.json", Product[].class); // s ovim smo iz products.json array izvukli array tipa Product, i sad mozemo proci for petljom kroz ovaj niz i izvuci nas zeljeni product

        //prolazimo sad kroz ovaj Product array i mozemo izvuci nas zeljeni product
        for(Product product: products){
            //product.id to je ovo product.getId() isto samo napisano ovako
            //ako je product.getId() jednak id koji obezbjedjuemo kao argument to znaci da smo dobili nas product
            //ako smo dobili nas product onda mozemo postaviti vrijednosti za nase varijable
            if(product.getId() == id){
                // s ovim smo postavili name i id varijable za Product object i onda mozemo to koristit u nasoj test class, s ovim je product object kreiran
                this.id = id;
                this.name = product.getName();
            }
        }
    }

    //getter metoda
    public int getId() {
        return id;
    }

    //setter metoda
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
