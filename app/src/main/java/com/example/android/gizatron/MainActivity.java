package com.example.android.gizatron;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        Fragment fragment = new RestaurantsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        handleDrawerSelection(menuItem);
                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A method to handle the selection of items from the NavigationDrawer menu
     *
     * @param menuItem is the selected item
     */
    public void handleDrawerSelection(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.restaurants:
                fragmentClass = RestaurantsFragment.class;
                break;
            case R.id.bars:
                fragmentClass = BarsFragment.class;
                break;
            case R.id.coffee:
                fragmentClass = CoffeeFragment.class;
                break;
            case R.id.delivery:
                fragmentClass = DeliveryFragment.class;
                break;
            case R.id.parks:
                fragmentClass = ParksFragment.class;
                break;
            case R.id.gyms:
                fragmentClass = GymsFragment.class;
                break;
            case R.id.art:
                fragmentClass = ArtFragment.class;
                break;
            case R.id.attractions:
                fragmentClass = AttractionsFragment.class;
                break;
            case R.id.nightlife:
                fragmentClass = NightlifeFragment.class;
                break;
            case R.id.live_music:
                fragmentClass = LiveMusicFragment.class;
                break;
            case R.id.movies:
                fragmentClass = MoviesFragment.class;
                break;
            case R.id.museums:
                fragmentClass = MuseumsFragment.class;
                break;
            case R.id.libraries:
                fragmentClass = LibrariesFragment.class;
                break;
            case R.id.groceries:
                fragmentClass = GroceriesFragment.class;
                break;
            case R.id.beauty_supplies:
                fragmentClass = BeautySuppliesFragment.class;
                break;
            case R.id.car_dealers:
                fragmentClass = CarDealersFragment.class;
                break;
            case R.id.home_and_garden:
                fragmentClass = HomeAndGardenFragment.class;
                break;
            case R.id.apparel:
                fragmentClass = ApparelFragment.class;
                break;
            case R.id.shopping_centers:
                fragmentClass = ShoppingCentersFragment.class;
                break;
            case R.id.electronics:
                fragmentClass = ElectronicsFragment.class;
                break;
            case R.id.sporting_goods:
                fragmentClass = SportingGoodsFragment.class;
                break;
            case R.id.convenience_stores:
                fragmentClass = ConvenienceStoresFragment.class;
                break;
            case R.id.hotels:
                fragmentClass = HotelsFragment.class;
                break;
            case R.id.atm:
                fragmentClass = ATMsFragment.class;
                break;
            case R.id.beauty_salons:
                fragmentClass = BeautySalonsFragment.class;
                break;
            case R.id.car_rental:
                fragmentClass = CarRentalFragment.class;
                break;
            case R.id.car_wash:
                fragmentClass = CarWashFragment.class;
                break;
            case R.id.dry_cleaning:
                fragmentClass = DryCleaningFragment.class;
                break;
            case R.id.gas:
                fragmentClass = GasFragment.class;
                break;
            case R.id.hospitals_and_clinics:
                fragmentClass = HospitalsAndClinicsFragment.class;
                break;
            case R.id.mail_and_shipping:
                fragmentClass = MailAndShippingFragment.class;
                break;
            case R.id.parking:
                fragmentClass = ParkingFragment.class;
                break;
            default:
                fragmentClass = RestaurantsFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        assert fragment != null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack(null).commit();
        // Highlight the selected item
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawerLayout.closeDrawers();
    }
}
