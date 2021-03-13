package com.games.mario.tools;

import com.games.mario.MarioBros;

public class GameMaths {

    public static float scaledValue(float value) {
        return value / MarioBros.PPM;
    }

}
