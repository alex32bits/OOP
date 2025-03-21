class Remorca{
    private int cutii;
    private String numarInmatriculare;
    private static int anteriorCutii=9;

    public Remorca(int cutii,String numarInmatriculare){
        this.cutii=cutii;
        this.numarInmatriculare=numarInmatriculare;
        anteriorCutii=cutii;
    }

    public Remorca(String numarInmatriculare){
        this.numarInmatriculare=numarInmatriculare;
        anteriorCutii++;
        cutii=anteriorCutii;
    }

    public String toString(){
        return "R("+cutii+" "+numarInmatriculare+")   ";
    }

    public int getCutii(){
        return cutii;
    }

    public String getNumar(){
        return numarInmatriculare;
    }
}

class Tir{
    private Remorca[] arr=new Remorca[5];
    private int numarRemorci=0;

    public boolean adaugaRemorca(int nrCutii,String nrInmat){
        if(numarRemorci<5)
        {
            arr[numarRemorci]=new Remorca(nrCutii,nrInmat);
            numarRemorci++;
            return true;
        }
        return false;
    }

    public boolean adaugaRemorca(Remorca r){
        if(numarRemorci<5)
        {
            arr[numarRemorci]=r;
            numarRemorci++;
            return true;
        }
        return false;
    }

    public Remorca stergeRemorca(String nrInmat){
        for(int i=0;i<numarRemorci;i++)
        {
            if(arr[i].getNumar().equals(nrInmat))
            {
                Remorca aux=arr[i];
                for(int j=i+1;j<numarRemorci;j++)
                {
                    arr[j-1]=arr[j];
                }
                numarRemorci--;
                arr[numarRemorci]=null;
                return aux;
            }
        }
        return null;

    }

    public int getNrCutii(){
        int s=0;
        for(int i=0;i<numarRemorci;i++)
         s=s+this.arr[i].getCutii();

         return s;
    }

    public boolean equals(Object o){
        if(o instanceof Tir)
        {
            Tir copie=(Tir) o;
            if(this.getNrCutii()==copie.getNrCutii())
            {
                System.out.println("tirurile sunt identice");
                return true;
            }
        }
        System.out.println("tirurile nu sunt identice");
        return false;
    }

   public String toString(){
    String text="T->";
    for(int i=0;i<numarRemorci;i++)
    {
        text=text+arr[i].toString();
    }
    return text;
   }

}

class Main{
    public static void main(String[] args) {
        Remorca r1=new Remorca(7,"TM86SRE");
        Remorca r2=new Remorca(10,"TM16MIM");
        Remorca r3=new Remorca(14,"TM56ALX");
        Remorca r4=new Remorca("SB80PAS");
        Remorca r5=new Remorca(12,"SB81PAS");

        Tir t1=new Tir();
        t1.adaugaRemorca(r1);
        t1.adaugaRemorca(r2);
        t1.adaugaRemorca(r5);

        Tir t2=new Tir();
        t2.adaugaRemorca(r3);
        t2.adaugaRemorca(r4);

        System.out.println(t1);
        System.out.println(t2);

        t1.equals(t2);

        t1.stergeRemorca("SB81PAS");

        System.out.println(t1);
        System.out.println(t2);


    }
}