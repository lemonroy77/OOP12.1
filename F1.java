interface ILandForce {;}//陆军
interface IAirForce {;} //空军
interface IAttackLandForce{//可对地攻击
    //由于仅输出信息，为避免重复书写，这里就加上了默认方法。注：必须用JDK1.8版
    default void attack(ILandForce x){ System.out.print("可以攻击陆军");}
}
interface IAttackAirForce{//可对空攻击
    default void attack(IAirForce x){ System.out.print("可以攻击空军");}
}
interface IAttackAirLandForce extends IAttackLandForce,IAttackAirForce{;}//可对地对空攻击
abstract class Arms{//兵种类
    private String type;
    public Arms(String n){ type=n; }
    public abstract void attack(Arms x);    //所有兵种都能攻击
    public String attackInfo(Arms x){    return type+" 遇见 "+x.type; }
}
abstract class AirVehicle extends Arms implements IAirForce{//飞行器类
    public AirVehicle(String n){super(n); }
}
class Bomber extends AirVehicle implements IAttackLandForce{//轰炸机类
    public Bomber(){super(" 轰炸机 "); }
    public void attack(ILandForce x){ System.out.print("轰炸机");}
    public void attack(IAirForce x){ System.out.print("轰炸机");}
    public void attack(Arms x){//轰炸机只能攻击陆军。攻击前需对目标进行识别
        System.out.print("\n"+attackInfo(x)+"：");
        if(x instanceof ILandForce )attack((ILandForce)x);
        else System.out.print("不能攻击！");
        if(x instanceof Soldier) System.out.print("用炸弹攻击士兵");
        if(x instanceof LightTank) System.out.print("用炸弹攻击轻型坦克");
        //其他兵种攻击方式同理
    }
}
class Helicopter extends AirVehicle implements IAttackAirLandForce{//直升机类
    public Helicopter(){super(" 直升机 "); }
    public void attack(ILandForce x){ System.out.print("直升机");}
    public void attack(IAirForce x){ System.out.print("直升机");}
    public void attack(Arms x){//直升机可对空、对地攻击
        System.out.print("\n"+attackInfo(x)+"：");
        if(x instanceof ILandForce )attack((ILandForce)x);
        else if(x instanceof IAirForce )attack((IAirForce)x);
        else System.out.print("不能攻击！");
        if(x instanceof Soldier) System.out.print("用机枪攻击士兵");
        if(x instanceof Bomber) System.out.print("用导弹攻击轰炸机");
        //其他兵种攻击方式同理
    }
}
abstract class Tank extends Arms implements ILandForce{//坦克类
    public Tank(String n){super(n); }
}
class HeavyTank extends Tank  implements IAttackAirLandForce {//重型坦克类
    public HeavyTank(){super("重型坦克"); }
    //重写接口IAttackAirLandForce中的缺省方法
    public void attack(ILandForce x){ System.out.print("重型坦克");}
    public void attack(IAirForce x){ System.out.print("重型坦克");}
    public void attack(Arms x){//重型坦克可对空、对地攻击
        System.out.print("\n"+attackInfo(x)+"：");
        if(x instanceof ILandForce )attack((ILandForce)x);
        else if(x instanceof IAirForce )attack((IAirForce)x);
        else System.out.print("不能攻击！");
        if(x instanceof Soldier) System.out.print("用重炮攻击士兵");
        if(x instanceof Bomber) System.out.print("用导弹攻击轰炸机");
        //其他兵种攻击方式同理
    }
}
class LightTank extends Tank  implements IAttackLandForce{//轻型坦克类
    public LightTank(){super("轻型坦克"); }
    public void attack(ILandForce x){ System.out.print("轻型坦克");}
    public void attack(IAirForce x){ System.out.print("轻型坦克");}
    public void attack(Arms x){//轻型坦克只能对地攻击
        System.out.print("\n"+attackInfo(x)+"：");
        if(x instanceof ILandForce )attack((ILandForce)x);
        else System.out.print("不能攻击！");
        if(x instanceof Soldier) System.out.print("用机枪攻击士兵");
        //其他兵种攻击方式同理
    }
}
abstract class Soldier extends Arms{//士兵类
    public Soldier(String n){super(n);}
}
class Infantry extends Soldier  implements ILandForce,IAttackLandForce{//步兵类
    public Infantry(){super("  步兵  "); }
    public void attack(ILandForce x){ System.out.print("步兵");}
    public void attack(IAirForce x){ System.out.print("步兵");}
    public void attack(Arms x){//步兵只能对地攻击
        System.out.print("\n"+attackInfo(x)+"：");
        if(x instanceof ILandForce )attack((ILandForce)x);
        else System.out.print("不能攻击！");
        if(x instanceof Tank) System.out.print("用步枪攻击坦克");
        //其他兵种攻击方式同理
    }
}
class FlyingSoldier extends Soldier  implements IAirForce,IAttackAirLandForce{//飞行兵类
    public FlyingSoldier(){super(" 飞行兵 "); }
    public void attack(ILandForce x){ System.out.print("飞行兵");}
    public void attack(IAirForce x){ System.out.print("飞行兵");}
    public void attack(Arms x){//飞行兵可对空、对地攻击
        System.out.print("\n"+attackInfo(x)+"：");
        if(x instanceof ILandForce )attack((ILandForce)x);
        else if(x instanceof IAirForce )attack((IAirForce)x);
        else System.out.print("不能攻击！");
        if(x instanceof Tank) System.out.print("用机枪攻击坦克");
        if(x instanceof Bomber) System.out.print("用导弹攻击轰炸机");
        if(x instanceof Helicopter) System.out.print("用导弹攻击直升机");
        //其他兵种攻击方式同理
    }
}
class App{
    public static void main (String[] args) {
        Arms[]a={new HeavyTank(), new HeavyTank(), new LightTank(), new LightTank(),new Bomber(),
            new FlyingSoldier(), new FlyingSoldier(),new Infantry(), new Infantry()};
        Arms[]b={new Bomber(),new Infantry(), new Infantry(), new Helicopter(),new Infantry(),
            new Helicopter(), new LightTank(),new LightTank(), new Bomber()};
        for(int i=0; i<a.length;i++) a[i].attack(b[i]);
    }
}
