package com.rojer_ko.homework.lesson_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rojer_ko.homework.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editTextRx;
    TextView textView;
    TextView textViewRx;
    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_2_activity_main);

        initViews();
        simpleMethod();
        rxJavaMethod();
    }

    void initViews(){
       editText = findViewById(R.id.editText);
       textView = findViewById(R.id.textView);
       editTextRx = findViewById(R.id.editTextRx);
       textViewRx = findViewById(R.id.textViewRx);
    }

    // обычная реализация
    void simpleMethod(){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = charSequence.toString();
                textView.setText(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    // реализация с RxJava
    void rxJavaMethod(){
        Observable<String> observable = Observable.create(emitter -> {
            try {
                TextWatcher watcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (!emitter.isDisposed()) {
                            emitter.onNext(editable.toString());
                        }
                    }
                };
                emitter.setCancellable(() -> editTextRx.removeTextChangedListener(watcher));
                editTextRx.addTextChangedListener(watcher);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onError(Throwable e) {
                Toast.makeText(getBaseContext(), "Ошибка" , Toast.LENGTH_SHORT).show();
                Log.d("Error", e.getMessage());
            }

            @Override
            public void onComplete() {
            }

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(String s) {
                textViewRx.setText(s);
            }
        };

        observable.subscribe(observer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
