
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.WeakHashMap;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int POSITION_ITEM = 0;

    private ListNeighbourActivity mActivity;
    private List<Neighbour> mFavNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }
    @Test
    public void clickItem_showDetailNeighbour () {
        // Select one neighbour
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM,click()));
        // Show neighbour detail
        onView(withId(R.id.acttivity_detail)).check(matches(isDisplayed()));
    }
    @Test
    public void when_displayDetailActivity_nameView_isNotEmpty() {
        // create a neighbour for test
        Neighbour neighbour = this.mFavNeighbours.get(POSITION_ITEM);
        // display detail activity
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM,click()));
        // check texteview is not empty
        onView(withId(R.id.nameProfil)).check (matches(withText(neighbour.getName())));


    }
    @Test
    public void favoriteTab_show_only_favoritesList() {
        // show detail neighbour
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM,click()));
        onView(withId(R.id.acttivity_detail)).check(matches(isDisplayed()));
        // click on fav button
        onView(withId(R.id.floatingActionButtonFav)).perform(click());
        // go back
        onView(withId(R.id.imageButtonBack)).perform(click());
        // select favorites list
        onView(withId(R.id.container)).perform(scrollRight());
        // check the list of favorites contains one neighbour
        onView(withId(R.id.fav_list_neighbours)).check(matches(isDisplayed()));
        onView(withId(R.id.fav_list_neighbours)).check(withItemCount(1));


    }
    @Test
    public void myFavNeighboursList_deleteAction_shouldRemoveItem() {
        // show detail neighbour
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_ITEM,click()));
        onView(withId(R.id.acttivity_detail)).check(matches(isDisplayed()));
        // click on fav button
        onView(withId(R.id.floatingActionButtonFav)).perform(click());
        // go back
        onView(withId(R.id.imageButtonBack)).perform(click());
        // select favorites list
        onView(withId(R.id.container)).perform(scrollRight());
        // check the list of favorites contains one neighbour
        onView(withId(R.id.fav_list_neighbours)).check(matches(isDisplayed()));
        onView(withId(R.id.fav_list_neighbours)).check(withItemCount(1));
        // delete this neighbour
        onView(withId(R.id.fav_list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        // check the list is empty
        onView(withId(R.id.fav_list_neighbours)).check(matches(hasMinimumChildCount(0)));

}
}