package com.apyan.tastette

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.apyan.tastette.fragment.HomeFragment
import com.apyan.tastette.fragment.PantryFragment
import com.apyan.tastette.fragment.SavedFragment
import com.apyan.tastette.fragment.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val TAG_FRAGMENT_HOME : String = "homeFragment"
    private val TAG_FRAGMENT_SEARCH : String = "searchFragment"
    private val TAG_FRAGMENT_PANTRY : String = "pantryFragment"
    private val TAG_FRAGMENT_SAVED : String = "savedFragment"

    private lateinit var mToolbar : ActionBar
    private lateinit var mBottonNavigationView : BottomNavigationView

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.bottomNavigationHome -> {
                val chartsFragment = HomeFragment.getInstance()
                navigateFragment(chartsFragment, TAG_FRAGMENT_HOME)
                hideOtherFragments(TAG_FRAGMENT_HOME)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottomNavigationSearch -> {
                val exchangeFragment = SearchFragment.getInstance()
                navigateFragment(exchangeFragment, TAG_FRAGMENT_SEARCH)
                hideOtherFragments(TAG_FRAGMENT_SEARCH)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottomNavigationPantry -> {
                val locateFragment = PantryFragment.getInstance()
                navigateFragment(locateFragment, TAG_FRAGMENT_PANTRY)
                hideOtherFragments(TAG_FRAGMENT_PANTRY)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottomNavigationSaved -> {
                val notesFragment = SavedFragment.getInstance()
                navigateFragment(notesFragment, TAG_FRAGMENT_SAVED)
                hideOtherFragments(TAG_FRAGMENT_SAVED)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Eliminate Toolbar
        mToolbar = supportActionBar!!
        mToolbar.hide()

        mBottonNavigationView = findViewById(R.id.navigationView)
        mBottonNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // Initialize with Home Fragment
        val initialFragment = HomeFragment.getInstance()
        navigateFragment(initialFragment, TAG_FRAGMENT_HOME)
        hideOtherFragments(TAG_FRAGMENT_HOME)
    }

    private fun navigateFragment(fragment: Fragment, tag: String?) {
        // Exists, display it
        if (supportFragmentManager.findFragmentByTag(tag) != null) {
            supportFragmentManager.beginTransaction().show(
                supportFragmentManager.findFragmentByTag(
                    tag
                )!!
            ).commit()
        } else {
            // Otherwise, add it
            supportFragmentManager.beginTransaction().add(R.id.container, fragment, tag).commit()
        }
        hideAllKeyboard()
    }

    private fun hideOtherFragments(tag: String?) {
        // The other fragment is visible, hide it
        if(supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_HOME) != null
            && !tag.equals(TAG_FRAGMENT_HOME)) {
            supportFragmentManager.beginTransaction().hide(
                supportFragmentManager.findFragmentByTag(
                    TAG_FRAGMENT_HOME
                )!!
            ).commit()
        }
        if(supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_SEARCH) != null
            && !tag.equals(TAG_FRAGMENT_SEARCH)) {
            supportFragmentManager.beginTransaction().hide(
                supportFragmentManager.findFragmentByTag(
                    TAG_FRAGMENT_SEARCH
                )!!
            ).commit()
        }
        if(supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_PANTRY) != null
            && !tag.equals(TAG_FRAGMENT_PANTRY)) {
            supportFragmentManager.beginTransaction().hide(
                supportFragmentManager.findFragmentByTag(
                    TAG_FRAGMENT_PANTRY
                )!!
            ).commit()
        }
        if(supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_SAVED) != null
            && !tag.equals(TAG_FRAGMENT_SAVED)) {
            supportFragmentManager.beginTransaction().hide(
                supportFragmentManager.findFragmentByTag(
                    TAG_FRAGMENT_SAVED
                )!!
            ).commit()
        }
    }

    private fun hideAllKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            currentFocus?.windowToken,
            InputMethodManager.RESULT_UNCHANGED_SHOWN
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}