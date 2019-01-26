package hanks.com.mylibrary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hanks.com.mylibrary.base.HImageLoader;


public class GridImageActivity extends Activity {

    public static final String EXTRA_PATH = "path";
    boolean isDirShowing = false;
    private View view_layer;
    private TextView tv_title;
    private RecyclerView recycler_image;
    private RecyclerView recycler_dir;
    private ImageAdapter imageAdapter;
    private ArrayList<Folder> mDirPaths = new ArrayList<>();
    /**
     * 已选择的图片
     */
    private ArrayList<String> selectedPicture = new ArrayList<>();
    private String cameraPath = null;
    private Folder imageAll, currentImageFolder;
    private FolderAdapter dirAdapter;
    private HImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_picture);
        bindViews();
        initViews();
        bindListeners();
    }

    public void back(View view) {
        onBackPressed();
    }

    private void bindViews() {
        view_layer = findViewById(R.id.view_layer);
        tv_title = (TextView) findViewById(R.id.tv_title);
        recycler_image = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_dir = (RecyclerView) findViewById(R.id.recycler_dir);
        view_layer.setVisibility(View.GONE);
    }

    private void initViews() {
        imageLoader = HGallery.getImageLoader(this);
        imageAll = new Folder();
        imageAll.setDir("/所有图片");
        currentImageFolder = imageAll;
        mDirPaths.add(imageAll);
        getThumbnail();
    }

    private void bindListeners() {
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDirlistPop();
            }
        });

        //图片列表
        GridLayoutManager mgr = new GridLayoutManager(this, 3);
        recycler_image.setLayoutManager(mgr);
        recycler_image.addItemDecoration(new GridSpacingItemDecoration(3, dp2px(5), false));
        imageAdapter = new ImageAdapter();
        recycler_image.setAdapter(imageAdapter);

        //目录列表
        recycler_dir.setLayoutManager(new LinearLayoutManager(this));
        dirAdapter = new FolderAdapter();
        recycler_dir.setAdapter(dirAdapter);

        //黑色图层
        view_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideDirList();
            }
        });
    }

    private void toggleDirlistPop() {
        if (isDirShowing) {
            hideDirList();
        } else {
            showDirlist();
        }
    }

    private void hideDirList() {
        view_layer.animate().alpha(0).setDuration(300).start();

        recycler_dir.animate().translationY(-dp2px(310)).setDuration(300).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isDirShowing = false;
                view_layer.setVisibility(View.GONE);
            }
        }).start();
    }

    private void showDirlist() {
        view_layer.setVisibility(View.VISIBLE);
        view_layer.animate().alpha(1).setDuration(300).start();
        recycler_dir.animate().translationY(0).setDuration(300).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isDirShowing = true;
            }
        }).start();
    }


    /**
     * 得到缩略图
     */
    private void getThumbnail() {
        /**
         * 临时的辅助类，用于防止同一个文件夹的多次扫描
         */
        HashMap<String, Integer> tmpDir = new HashMap<String, Integer>();

        Cursor mCursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.ImageColumns.DATA}, "", null,
                MediaStore.MediaColumns.DATE_ADDED + " DESC");
        Log.e("TAG", mCursor.getCount() + "");
        if (mCursor.moveToFirst()) {
            do {
                // 获取图片的路径
                String path = mCursor.getString(0);
                Log.e("TAG", path);
                imageAll.images.add(new ImageItem(path));
                // 获取该图片的父路径名
                File parentFile = new File(path).getParentFile();
                if (parentFile == null) {
                    continue;
                }
                Folder imageFloder = null;
                String dirPath = parentFile.getAbsolutePath();
                if (!tmpDir.containsKey(dirPath)) {
                    // 初始化imageFloder
                    imageFloder = new Folder();
                    imageFloder.setDir(dirPath);
                    imageFloder.setFirstImagePath(path);
                    mDirPaths.add(imageFloder);
                    Log.d("zyh", dirPath + "," + path);
                    tmpDir.put(dirPath, mDirPaths.indexOf(imageFloder));
                } else {
                    imageFloder = mDirPaths.get(tmpDir.get(dirPath));
                }
                imageFloder.images.add(new ImageItem(path));
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        for (int i = 0; i < mDirPaths.size(); i++) {
            Folder f = mDirPaths.get(i);
            Log.d("zyh", i + "-----" + f.getName() + "---" + f.images.size());
        }
        tmpDir = null;
    }

    private void resetDirList(Folder selectFolder) {
        currentImageFolder = selectFolder;
        dirAdapter.notifyDataSetChanged();
        imageAdapter.notifyDataSetChanged();
        hideDirList();
    }

    public int dp2px(int dp) {
        float scale = getResources().getDisplayMetrics().densityDpi;
        return (int) (dp * scale / 160 + 0.5f);
    }

    private void showToast(String string) {
        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            data.putExtra(EXTRA_PATH, resultUri.getPath());
            setResult(RESULT_OK, data);
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
            Toast.makeText(GridImageActivity.this, cropError.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

        @Override
        public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
            return new ImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ImageViewHolder holder, int position) {
            final ImageItem item = currentImageFolder.images.get(position);
            imageLoader.displayImage(holder.imageView, item.path);
            holder.tv_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String destinationFileName = item.path.hashCode() + "";
                    Uri sourceUri = Uri.fromFile(new File(item.path));
                    Uri destinationUri = Uri.fromFile(new File(getCacheDir(), destinationFileName));
                    UCrop.Options options = new UCrop.Options();
                    options.setStatusBarColor(Color.BLACK);
                    options.setToolbarColor(Color.WHITE);
                    options.setToolbarWidgetColor(Color.BLACK);
                    options.setActiveWidgetColor(Color.RED);
                    UCrop.of(sourceUri, destinationUri)
                            .withOptions(options)
                            .start(GridImageActivity.this);
//                    ClipImageActivity.launch(GridImageActivity.this, item.path);
                }
            });
        }

        @Override
        public int getItemCount() {
            return currentImageFolder.images.size();
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public View tv_click;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv);
            tv_click = itemView.findViewById(R.id.tv_click);
        }

    }

    class FolderAdapter extends RecyclerView.Adapter<FolderViewHolder> {
        @Override
        public FolderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dir, parent, false);
            return new FolderViewHolder(view);
        }

        @Override
        public void onBindViewHolder(FolderViewHolder holder, int position) {
            final Folder item = mDirPaths.get(position);
            imageLoader.displayImage(holder.iv_dir, item.getFirstImagePath());
            String name = item.name.startsWith("/") ? item.name.substring(1) : item.name;
            holder.tv_dirname.setText(name + " (" + item.images.size() + "张) ");
            holder.ll_root.setSelected(currentImageFolder == item);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetDirList(item);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDirPaths.size();
        }
    }

    class FolderViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_dir;
        public TextView tv_dirname;
        public View ll_root;

        public FolderViewHolder(View itemView) {
            super(itemView);
            ll_root = itemView.findViewById(R.id.ll_root);
            iv_dir = (ImageView) itemView.findViewById(R.id.iv_dir);
            tv_dirname = (TextView) itemView.findViewById(R.id.tv_dirname);
        }
    }

    class Folder {


        public List<ImageItem> images = new ArrayList<ImageItem>();
        /**
         * 图片的文件夹路径
         */
        private String dir;
        /**
         * 第一张图片的路径
         */
        private String firstImagePath;
        /**
         * 文件夹的名称
         */
        private String name;

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
            int lastIndexOf = this.dir.lastIndexOf("/");
            this.name = this.dir.substring(lastIndexOf);
        }

        public String getFirstImagePath() {
            return firstImagePath;
        }

        public void setFirstImagePath(String firstImagePath) {
            this.firstImagePath = firstImagePath;
        }

        public String getName() {
            return name;
        }

    }

    class ImageItem {

        String path;

        public ImageItem(String p) {
            this.path = p;
        }

    }

    class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}
