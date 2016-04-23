package com.beltranfebrer.discogsbrowser.di;

import com.beltranfebrer.discogsbrowser.di.modules.DiscogsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltranfebrer.com
 */
@Singleton
@Component(modules = {DiscogsModule.class})
public interface TestComponent {
}
