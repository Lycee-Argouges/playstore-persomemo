package fr.argouges.persomemo.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;


public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return null;
            //return "Hello world from section: " + input;
            /*String TextR = new String();
            if (input==1) {
                TextR =  "Ceci est la page de la consultation";
            }
            if (input==2) {
                TextR = "Celle-ci pour la rédaction";
            }
            return TextR;*/
        }
    });


    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}