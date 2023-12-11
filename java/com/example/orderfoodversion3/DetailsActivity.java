package com.example.orderfoodversion3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailsActivity extends AppCompatActivity {
    TextView detailDesc, detailTitle , QteNum , total , prix ;
    ImageView detailImage;
    Button moins , plus  , panier ;
    int nbp=1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        prix=findViewById(R.id.prix);
        QteNum=findViewById(R.id.qte);
        total=findViewById(R.id.total);
        moins=findViewById(R.id.moins);
        plus=findViewById(R.id.plus);
        panier=findViewById(R.id.panier);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            prix.setText("Prix : "+bundle.getLong("prix"));
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);


        }
        moins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (nbp>=1) {


                        nbp--;
                        QteNum.setText(String.valueOf(nbp));
                        Long qteFood = getIntent().getExtras().getLong("prix");
                        total.setText(String.valueOf(nbp * qteFood));
                    }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nbp++;
                QteNum.setText(String.valueOf(nbp));
                Long  qteFood =getIntent().getExtras().getLong("prix");

                total.setText(String.valueOf(nbp*qteFood));

            }
        });


panier.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        addFoodToCart();
        Toast.makeText(DetailsActivity.this, "add to cart", Toast.LENGTH_SHORT).show();
    }
});

    }
    private void addFoodToCart() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        // Check if the user is authenticated
        if (auth.getCurrentUser() != null) {
            String userId = auth.getCurrentUser().getUid();

            // Reference to the user's cart
            DatabaseReference userCartRef = database.child("users").child(userId).child("panier");

            // Create a unique key for the new entry in the cart
            String cartItemId = userCartRef.push().getKey();

            // Create a DataCart object with the food information
            DataCart dataCart = new DataCart(
                    getIntent().getExtras().getString("Title"),
                    nbp,
                    getIntent().getExtras().getString("Description"),
                    nbp*getIntent().getExtras().getLong("prix")
            );

            // Add the DataCart object to the user's cart under the unique key
            userCartRef.child(cartItemId).setValue(dataCart);

            // Display a success message or perform any additional actions if needed
            // Toast.makeText(this, "Food added to cart successfully", Toast.LENGTH_SHORT).show();
        }
    }


}