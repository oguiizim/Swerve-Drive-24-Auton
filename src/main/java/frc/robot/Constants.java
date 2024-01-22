// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import swervelib.math.Matter;
import swervelib.math.SwerveMath;
import swervelib.parser.PIDFConfig;

/**
 * Classe de constantes
 */
public final class Constants {
  // Aqui temos várias constantes referentes as demais áreas do robô

  public static final class Dimensoes {
    // Tempo de loop (sparkMax + normal = 130ms)
    public static final double LOOP_TIME = 0.13;
    // Massa do robô
    public static final double ROBOT_MASS = 38;

    // Posições do centro de massa
    private static final double xMass = 0;
    private static final double yMass = 0;
    private static final double zMass = .08;
    // Centro de massa do chassi
    public static final Matter CHASSIS = new Matter(new Translation3d(xMass, yMass, (zMass)), ROBOT_MASS);

    // Máxima aceleração e velocidade
    public static final double MAX_ACCE_AUTO = 1;
    public static final double MAX_VEL_AUTO = 1;

    // Diâmetro da roda do módulo
    public static final double wheelDiameterInMeters = Units.inchesToMeters(4);
    public static final double coletorDiameterInMeters = Units.inchesToMeters(2);

    // Redução para motor de acionamento e ângulo
    public static final double driveGearRatio = 6.18;
    public static final double angleGearRatio = 21.42;

    // PPR do encoder interno NEO;
    public static final double pulsePerRotation = 1;

    // Fatores de conversão para motores de acionamento e ângulo
    public static final double driveConversion = SwerveMath.calculateMetersPerRotation(wheelDiameterInMeters,
        driveGearRatio, pulsePerRotation);
    public static final double angleConversion = SwerveMath.calculateDegreesPerSteeringRotation(angleGearRatio,
        pulsePerRotation);
  }

  // Classe que contém os PID para o autônomo
  public static final class PID {
    // PID para frente e para trás
    public static final PIDFConfig xAutoPID = new PIDFConfig(0.65, 0, 0.05);
    // PID para esquerda e direita
    public static final PIDFConfig yAutoPID = new PIDFConfig(0.6, 0, 0);
    // PID de rotação
    public static final PIDFConfig angleAutoPID = new PIDFConfig(0.1, 0, 0.01);
  }

  public static final class Motors {
    // ID SparkMax intake
    public static final int coletor1 = 9;
    public static final int coletor2 = 10;

    // ID SparkMax shooter
    public static final int lancador1 = 11;
    public static final int lancador2 = 12;
    public static final int lancador3 = 13;

    // ID SparkMax escalator
    public static final int gancho = 14;
  }

  // Contem a porta em que o controle está
  public static final class Controle {
    // Porta dos controles
    public static final int xboxControle = 0;
    public static final int controle2 = 1;

    // Deadband do controle
    public static final double DEADBAND = 0.2;

    // Eixos do controle
    public static final int xLeftAxis = 0;
    public static final int yLeftAxis = 1;
    public static final int leftTrigger = 2;
    public static final int rightTrigger = 3;
    public static final int xRightAxix = 4;
    public static final int yRightAxis = 5;

    // Botões do controle
    public static final int kA = 1;
    public static final int kB = 2;
    public static final int kX = 3;
    public static final int kY = 4;
    public static final int kLB = 5;
    public static final int kRB = 6;
  }

  public static final class Tracao {
    // Define se a tração vai ser orientada ao campo (sim = true)
    public static final boolean fieldRelative = true;
    // false para malha-fechada
    public static final boolean isOpenLoop = false;
    // true para correção de aceleração
    public static final boolean accelCorrection = false;
    // constante para diminuir o input do joystick (0 < multiplicadorRotacional <=
    // 1)
    public static final double multiplicadorRotacional = 0.8;
    // constante para diminuir o input do joystick (y)
    public static final double multiplicadorTranslacionalY = 0.7;
    // constante para diminuir o input do joystick (x)
    public static final double multiplicadorTranslacionalX = 0.7;

    public static final double TURN_CONSTANT = 0.75;

    // constante que define a velocidade máxima
    public static final double MAX_SPEED = 4.8;

    public static final double dt = 0.02;

    public static final double constantRotation = 4;

    // Velocidade de cada parte do intake
    public static final double coletorSpd = 1;
    public static final double ganhcoSpd = 0.5;
    public static final double lancadorMax = 1;
    public static final double lancadorMid = 1;
    public static final double lancadorAmp = 1;

  }

  // Classe que guarda os nomes das trajetórias
  public static final class Trajetoria {
    public static final boolean ALIANCA = true; // Caso a aliança seja azul use false, se for vermelha use true
    public static final String NOME_TRAJETORIA = "AutoTeste";
    public static final String NOME_TRAJETORIA2 = "New Path2";
    public static final String NOME_TRAJETORIA3 = "New Path3";
  }
}
