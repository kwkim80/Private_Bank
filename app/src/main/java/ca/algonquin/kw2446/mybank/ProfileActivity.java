package ca.algonquin.kw2446.mybank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import ca.algonquin.kw2446.mybank.databinding.ActivityPofileBinding;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;
import ca.algonquin.kw2446.mybank.util.AppUtil;
import ca.algonquin.kw2446.mybank.util.PreferenceManager;
import ca.algonquin.kw2446.mybank.viewmodel.ProfileVM;

public class ProfileActivity extends AppCompatActivity {


    public static final int CAPTURE_IMAGE = 99;
    public static final int PICK_IMAGE =98 ;
    Bitmap bitmap;
    String filePath;
    ProfileVM vm;
    ActivityPofileBinding binding;
    boolean isImageSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_pofile);
        binding =DataBindingUtil.setContentView(this,R.layout.activity_pofile);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        vm= new ViewModelProvider(this).get(ProfileVM.class); //ViewModelProviders.of(this).get(ProfileVM.class);
        binding.setViewModel(vm);
        binding.setLifecycleOwner(this);

        binding.tvName.setText(vm.getName());
        binding.tvEmail.setText(vm.getEmail());
        if(vm.getPhoto()!=null){
            binding.ivPhoto.setImageBitmap(vm.getPhoto());
        }


        if(vm.getOriPwd().equalsIgnoreCase("0000")){
            binding.etPwd.setHint("                         (Initial Password is 0000)");
        }

        binding.btnUpdate.setOnClickListener((v)->{
            if(vm.setProfile(isImageSet, bitmap)){
                Intent intent=new Intent();
                setResult(RESULT_OK,intent);
                ProfileActivity.this.finish();
            }
        });
        binding.btnCancel.setOnClickListener(v->this.finish());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                ProfileActivity.this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void imgEdit(View view) {
        selectImage();
    }

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAPTURE_IMAGE);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, PICK_IMAGE);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAPTURE_IMAGE && resultCode == RESULT_OK && data != null){

            bitmap = (Bitmap) data.getExtras().get("data");
            binding.ivPhoto.setImageBitmap(bitmap);

            isImageSet=true;

        }
        if (requestCode==PICK_IMAGE &&resultCode == RESULT_OK) {

            Uri picUri = data.getData();
            filePath = AppUtil.getPath(getApplicationContext(), picUri);
            Log.d("picUri", picUri.toString());
            Log.d("filePath", filePath);
            binding.ivPhoto.setImageURI(picUri);
            bitmap=AppUtil.getBitmap(filePath);
            isImageSet=true;
        }

    }

}
