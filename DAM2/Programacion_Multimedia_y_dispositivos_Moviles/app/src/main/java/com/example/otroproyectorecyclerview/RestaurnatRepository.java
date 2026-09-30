package com.example.otroproyectorecyclerview;

public class RestaurnatRepository {

    val products = listOf(
            Product(1,"Patatas bravas", "https://thecookinglab.es/wp-content/uploads/2014/02/patatas-bravas-receta.jpg", 10.0, "ración"),
            Product(2, "Patatas alioli", "https://imag.bonviveur.com/patatas-alioli.jpg", 10.0, "ración")
    )

    fun getAllProducts(): List<Product>{
        return products;
    }

}
