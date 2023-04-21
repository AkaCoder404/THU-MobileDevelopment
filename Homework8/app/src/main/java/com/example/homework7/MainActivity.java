package com.example.homework7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button addProducerButton;
    private Button addConsumerButton;


    private List<Integer> store = new ArrayList<>();
    private final int MAX_STORE_SIZE = 10;
    private final int MAX_PRODUCTS_PRODUCE = 20;
    private final int PRODUCTION_INTERVAL_MS = 1000;
    private int numProductsProduced = 0;

    private volatile boolean isRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        addProducerButton = findViewById(R.id.addProducerButton);
        addConsumerButton = findViewById(R.id.addConsumerButton);

        addProducerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Producer()).start();
            }
        });


        addConsumerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Consumer()).start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }

    private synchronized void updateTextView(String message) {
        String threadName = Thread.currentThread().getName();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.append(threadName + message + "\n");
            }
        });
    }

    private class Product {
        private int id;

        public Product(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    private class Producer implements Runnable {
        private int nextProductId = 1;

        @Override
        public void run() {
            while (isRunning) {
                try {
                    Thread.sleep(PRODUCTION_INTERVAL_MS);

                    synchronized (MainActivity.this) {
                        if (store.size() < MAX_STORE_SIZE  && numProductsProduced < MAX_PRODUCTS_PRODUCE) {
                            Product product = new Product(nextProductId++);
                            store.add(product.getId());
                            numProductsProduced++;
                            updateTextView("生产了： #" + product.getId() +
                                    "，还剩：" + store.size());

                            if (numProductsProduced == MAX_PRODUCTS_PRODUCE) {
                                isRunning = false; // stop all threads
                            }
                        } else {
                            MainActivity.this.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private class Consumer implements Runnable {

        @Override
        public void run() {
            while (isRunning) {
                try {
                    Thread.sleep(PRODUCTION_INTERVAL_MS);

                    synchronized (MainActivity.this) {
                        if (!store.isEmpty()) {
                            int productId = store.remove(0);

                            updateTextView("消费了：" + productId +
                                    "，还剩： " + store.size());

                            MainActivity.this.notifyAll();
                        } else {
                            MainActivity.this.wait();
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}