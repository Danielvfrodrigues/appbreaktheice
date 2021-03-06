package br.com.bravi.breaktheice.presentation.activity;

import static org.koin.android.compat.ViewModelCompat.getViewModel;
import static br.com.bravi.breaktheice.databinding.ActivityMainBinding.inflate;
import static br.com.bravi.breaktheice.util.ActivityUtil.addFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.bravi.breaktheice.databinding.ActivityMainBinding;
import br.com.bravi.breaktheice.presentation.fragment.ActivityListFragment;
import br.com.bravi.breaktheice.presentation.viewmodel.MainViewModel;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    private MainViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        viewModel = getViewModel(MainActivity.this, MainViewModel.class);

        final ActivityMainBinding binding = inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.setLifecycleOwner(MainActivity.this);
        binding.setViewModel(viewModel);

        addFragment(getSupportFragmentManager(), ActivityListFragment.getInstance(bundle), binding.container.getId());

        fetchError();
    }

    private void fetchError() {
        viewModel.error.observe(MainActivity.this, err -> {
            if (err != null) {
                Log.e(TAG, err.getMessage());
            }
        });
    }
}
