import java.util.Date;
import java.util.Random;


class Out extends Exception{
    public Out(String mesaj){
        super(mesaj);
    }
}

class Gol extends Exception{
    public Gol(String mesaj){
        super(mesaj);
    }
}

class Corner extends Exception{
    public Corner(String mesaj){
        super(mesaj);
    }
}

class Minge{
    private int x;
    private int y;

    public Minge(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void suteaza(CoordinateGenerator generator) throws Out,Gol,Corner{
        this.x=generator.generateX();
        this.y=generator.generateY();

        if(y==0 || y==50)
        {
            throw new Out("mingea a iesit din teren");
        }

        if((x==0 || x==100) && (y>=20 && y<=30))
        {
            throw new Gol("este gollll");
        }

        if((x==0 || x==100) && ((y>0 && y<20) ||(y>30 && y<50)))
        {
            throw new Corner("este corner");
        }

    }

}



class CoordinateGenerator {
    private Random randomGenerator;

    public CoordinateGenerator() {
        Date now = new Date();
        long sec = now.getTime();
        randomGenerator = new Random(sec);
    }   

    public int generateX() {
        int x = randomGenerator.nextInt(101);
        if(x < 5) {
        x = 0;
    }   
    else 
    if(x > 95) 
    {
        x = 100;
    } 
    else 
    {
        x = randomGenerator.nextInt(99) + 1;
    }
    return x;
  }
    public int generateY() {
        int y = randomGenerator.nextInt(101);
        if(y < 5) 
        {
           y = 0;
        } 
        else 
        if(y > 95) 
        {
            y = 50;
        } 
        else 
        {
            y = randomGenerator.nextInt(49) + 1;
        }
    return y;
 }
}

class Joc{
    private String echipa1;
    private String echipa2;
    private int goluriEchipa1;
    private int goluriEchipa2;
    private int outuri; 
    private int cornere; 
    
    private int cornereEchipa2;  

    public Joc(String echipa1,String echipa2){
        this.echipa1=echipa1;
        this.echipa2=echipa2;
        this.goluriEchipa1=0;
        this.goluriEchipa2=0;
        this.outuri=0;
        this.cornere=0;

    }


    public String toString(){
        return echipa1+" vs "+echipa2+"   "+goluriEchipa1+"-"+goluriEchipa2+"   outuri: "+outuri+"  corenere: "+cornere+"  ";
    }

    public void simuleaza(){
        CoordinateGenerator generator = new CoordinateGenerator();
        Minge minge=new Minge(50,25);
        for(int i=0;i<1000;i++)
        {
            try{
                System.out.println(echipa1+"-"+echipa2+" :mingea("+minge.getX()+","+minge.getY()+") ");
                System.out.println();
                minge.suteaza(generator);
            }catch(Out e){
                System.out.print(e.getMessage());
                outuri++;
                minge=new Minge(minge.getX(),minge.getY());
            }catch(Gol e){
                System.out.println(e.getMessage());
                if(minge.getX()==0)
                {
                    goluriEchipa1++;
                }
                else{
                    goluriEchipa2++;
                }
                minge=new Minge(50,25);

            }catch(Corner e){
                System.out.println(e.getMessage());
                cornere++;
                if(minge.getX()==0 && minge.getY()<20)
                    minge=new Minge(0,0);
                if(minge.getX()==0 && minge.getY()>30)
                    minge=new Minge(0,50);
                if(minge.getX()==100 && minge.getY()<20)
                    minge=new Minge(100,0);
                if(minge.getX()==100 && minge.getY()>30)
                    minge=new Minge(100,50);

            }
        }
    }

    
}

class MainEx4{
    public static void main(String[] Args){
        Joc j1=new Joc("Barca","Real");
        j1.simuleaza();
        System.out.println(j1);
    }
}

