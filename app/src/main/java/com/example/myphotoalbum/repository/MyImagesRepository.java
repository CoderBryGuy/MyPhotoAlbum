package com.example.myphotoalbum.repository;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;
import com.example.myphotoalbum.database.MyImages;
import com.example.myphotoalbum.database.MyImagesDAO;
import com.example.myphotoalbum.database.MyImagesDatabase;

import java.util.List;

public class MyImagesRepository {

    private MyImagesDAO mMyImagesDAO;
    private LiveData<List<MyImages>> mImagesList;

    public MyImagesRepository(Application application) {
        MyImagesDatabase database = MyImagesDatabase.getInstance(application);
        mMyImagesDAO = database.mMyImagesDAO();
        mImagesList = mMyImagesDAO.getAllImages();

    }

    public void insert(MyImages myImages) {
        new InsertImageAsyncTask(mMyImagesDAO).execute(myImages);

    }

    public void delete(MyImages myImages) {
        new DeleteImageAsyncTask(mMyImagesDAO).execute(myImages);

    }

    public void update(MyImages myImages) {
        new UpdateImageAsyncTask(mMyImagesDAO).execute(myImages);
    }

    public LiveData<List<MyImages>> getAllImages() {
        return mImagesList;
    }

    private static class InsertImageAsyncTask extends AsyncTask<MyImages, Void, Void> {

        MyImagesDAO mMyImagesDAO;

        public InsertImageAsyncTask(MyImagesDAO myImagesDAO) {
            mMyImagesDAO = myImagesDAO;
        }

        @Override
        protected Void doInBackground(MyImages... myImages) {
            mMyImagesDAO.insert(myImages[0]);
            return null;
        }
    }

    private static class DeleteImageAsyncTask extends AsyncTask<MyImages, Void, Void> {

        MyImagesDAO mMyImagesDAO;

        public DeleteImageAsyncTask(MyImagesDAO myImagesDAO) {
            mMyImagesDAO = myImagesDAO;
        }

        @Override
        protected Void doInBackground(MyImages... myImages) {
            mMyImagesDAO.insert(myImages[0]);
            return null;
        }
    }

    private static class UpdateImageAsyncTask extends AsyncTask<MyImages, Void, Void> {
        MyImagesDAO mMyImagesDAO;

        public UpdateImageAsyncTask(MyImagesDAO myImagesDAO) {
            mMyImagesDAO = myImagesDAO;
        }

        @Override
        protected Void doInBackground(MyImages... myImages) {
            mMyImagesDAO.update(myImages[0]);
            return null;
        }
    }

}
