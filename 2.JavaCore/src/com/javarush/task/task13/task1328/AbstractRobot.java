package com.javarush.task.task13.task1328;

public abstract class AbstractRobot implements Attackable, Defensable{

    private static int hitCount;
    private final String name;

    public AbstractRobot(String name) {
        this.name = name;
    }
    @Override
    public BodyPart attack() {
        BodyPart attackedBodyPart;
        hitCount = hitCount + 1;

        if (hitCount == 1) {
            attackedBodyPart = BodyPart.ARM;
        } else if (hitCount == 2) {
            attackedBodyPart = BodyPart.HEAD;
        } else if (hitCount == 3) {
            attackedBodyPart = BodyPart.CHEST;
        }else  {
            hitCount = 0;
            attackedBodyPart = BodyPart.LEG;
        }
        return attackedBodyPart;
    }

    @Override
    public BodyPart defense() {
        BodyPart defendedBodyPart;
        hitCount = hitCount + 2;

        if (hitCount == 1) {
            defendedBodyPart = BodyPart.HEAD;
        } else if (hitCount == 2) {
            defendedBodyPart = BodyPart.LEG;
        } else if (hitCount == 3) {
            defendedBodyPart = BodyPart.CHEST;
        }else  {
            hitCount = 0;
            defendedBodyPart = BodyPart.ARM;
        }
        return defendedBodyPart;
    }
    public String getName() {
        return name;
    }
}
