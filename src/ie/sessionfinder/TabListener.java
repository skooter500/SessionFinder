package ie.sessionfinder;

import android.support.v4.app.Fragment;

public class TabListener<T extends Fragment> 
implements ActionBar.TabListener {

	private MyFragment fragment;
	private Activity activity;
	private Class<T> fragmentClass;
	private int fragmentContainer;

	public TabListener(Activity activity, int fragmentContainer, 
			Class<T> fragmentClass) {

		this.activity = activity;
		this.fragmentContainer = fragmentContainer;
		this.fragmentClass = fragmentClass;
	}

	// Called when a new tab has been selected
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		if (fragment == null) {
			String fragmentName = fragmentClass.getName();
			fragment =
					(MyFragment)Fragment.instantiate(activity, fragmentName);
			ft.add(fragmentContainer, fragment, null);
			fragment.setFragmentText(tab.getText());
		} else {
			ft.attach(fragment);
		}
	}

	// Called on the currently selected tab when a different tag is
	// selected. 
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		if (fragment != null) {
			ft.detach(fragment);
		}
	} 

	// Called when the selected tab is selected.
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO React to a selected tab being selected again.
	}
}
}