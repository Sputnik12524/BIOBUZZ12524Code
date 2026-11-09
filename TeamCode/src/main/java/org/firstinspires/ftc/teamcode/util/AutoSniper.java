package org.firstinspires.ftc.teamcode.util;

import org.firstinspires.ftc.teamcode.subsystem.Turret;

public class AutoSniper {
    public final Turret tt;

    public enum Alliance {NONE, BLUE, RED}

    //-------------------------------- GENERAL

    public double goalY = 0;
    public double goalX = 0;
    public final double goalZ = 0;
    public final double highOfShooting = 0;

    public final double z = goalZ - highOfShooting;

    public Alliance alliance = Alliance.NONE;

    public double target = 0;
    public double angleOfTurret;

    public final double sv = 0.017; // meters
    public double sX, sY, c, a, D, l = 0;

    public static final double MAX_ANGLE = 60;
    public static final double MIN_ANGLE = 45;
    public static final double POS_FOR_MAX_ANGLE = 1;
    public static final double POS_FOR_MIN_ANGLE = 0.815;

    public boolean AIMING_ACTIVE = true;

    final double ITM = 0.0254;
    final double g = 9.81;

    //--------------------------------------
    public AutoSniper(Turret turret) {
        tt = turret;
    }

    public void setGoalPos(double x, double y) {
        goalX = x;
        goalY = y;
    }

    public void setAlliance(Alliance alliance) {
        switch (alliance) {
            case BLUE:
                setGoalPos(0,0);
                this.alliance = Alliance.BLUE;
                break;
            case RED:
                setGoalPos(0,0);
                this.alliance = Alliance.RED;
                break;
            case NONE:
                this.alliance = Alliance.NONE;
        }
    }

    public void updateHiveCell(double y) {
        if (y>0) {
            setGoalPos(goalX, goalY+20);
        } else {
            setGoalPos(goalX, goalY);
        }
    }

    public void simpleContinuousTurnTurretToGate(double x, double y, double angleOfDrivetrain) {
        if (AIMING_ACTIVE) {
            if (x <= 0) x = 1;
            if (x >= 144) x = 143;
            switch (alliance) {
                case RED:
                    angleOfTurret = Math.toDegrees(Math.atan((goalY - (y + sY)) / (goalX - (x + sX))));
                    target = -(Math.toDegrees(angleOfDrivetrain) - angleOfTurret) + 70;
                    break;
                case BLUE:
                    angleOfTurret = 180 - Math.toDegrees(Math.atan((goalY - (y + sY)) / ((x + sX) - goalX)));
                    target = -(Math.toDegrees(angleOfDrivetrain) - angleOfTurret) + 70;
                    break;
                case NONE:
                    angleOfTurret = 0;
                    break;
            }
            target = tt.angleNormalising(target);
            tt.turnByTarget(target);

        } else {
            tt.turnByTarget(0);
        }
    }


    public void continuousTurnTurretToGate(double x, double y, double angleOfDrivetrain) {
        if (AIMING_ACTIVE) {
           //Крутая формула с учетом скорости робота
        } else {
            tt.turnByTarget(0);
        }
    }





    public void continuousCalculateGeneralValues(double x, double y, double angleOfDrivetrain, double angularVelocity) {
        sY = cMTI(sv * Math.sin(angleOfDrivetrain)); //inches
        sX = cMTI(sv * Math.cos(angleOfDrivetrain)); //inches
        l = cITM(Math.sqrt(Math.pow((goalY - (y + sY)), 2) + Math.pow((goalX - (x + sX)), 2))); //meters
        a = g * Math.pow(l, 2) / (2 * Math.pow(angularVelocity, 2));
        c = a - cITM(z);
        D = Math.pow(l, 2) - 4 * a * c;
    }



    public double cITM(double inch) { //Convert inches to meters
        return inch * ITM;
    }

    public double cMTI(double meter) { //Convert meters to inches
        return meter / ITM;
    }

}
