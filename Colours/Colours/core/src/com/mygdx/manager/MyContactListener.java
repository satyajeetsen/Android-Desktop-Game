package com.mygdx.manager;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;


public class MyContactListener implements ContactListener {

    private  int numFootContacts;
    private Array<Body> bodiesToRemove;
    private boolean playerIsDead ;
    private boolean playerCollidedWithSpike;


    public MyContactListener(){
        super();
        bodiesToRemove=new Array<Body>();
    }
//called when two fixtures start to collide
    @Override
    public void beginContact(Contact c) {
        Fixture fa =c.getFixtureA();
       //fa.setUserData("foot");
        Fixture fb =c.getFixtureB();
      //  fb.setUserData("diamonds");


        if(fa==null || fb==null){
            return;
        }
        if(fa.getUserData() != null && fa.getUserData().equals("foot")){
            numFootContacts++;

        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot")){
            numFootContacts++;

        }



        if(fa.getUserData() != null && fa.getUserData().equals("diamonds")){
                          //remove diamonds
            bodiesToRemove.add(fa.getBody());


        }
        if(fb.getUserData() != null && fb.getUserData().equals("diamonds")){
                             //remove diamonds
            bodiesToRemove.add(fb.getBody());
        }
        if (fa.getUserData() != null && fa.getUserData().equals("spikes")) {
            playerCollidedWithSpike = true;
            playerIsDead=true;
        }
        if (fb.getUserData() != null && fb.getUserData().equals("spikes")) {
            playerCollidedWithSpike = true;
            playerIsDead=true;
        }
       // System.out.println(fa.getUserData() + ", " + fb.getUserData());
    }
//called when two fixtures no longer collide
    @Override
    public void endContact(Contact c) {
        Fixture fa =c.getFixtureA();
        Fixture fb =c.getFixtureB();

        if(fa==null || fb==null){
            return;
        }
        if(fa.getUserData() != null && fa.getUserData().equals("foot")){
           numFootContacts--;
          // isPlayerOnGround();
        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot")){
           numFootContacts--;
          // isPlayerOnGround();
        }

    }

    @Override
    public void preSolve(Contact c, Manifold oldManifold) {

    }

    public Array<Body> getBodiesToRemove(){
        return bodiesToRemove;
    }

    @Override
    public void postSolve(Contact c, ContactImpulse impulse) {

    }

    //collision detection
    //presolve
    //collision handling
    //postsolve
   public boolean isPlayerOnGround()
   {
       return numFootContacts >=0;
   }
    public boolean isPlayerDead(){
        return playerIsDead;
    }

}
