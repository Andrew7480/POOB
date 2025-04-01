package maxwell;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Write a description of class MaxwellContest here.
 * 
 * @author Andrés Cardozo && Tulio Riaño
 * @version (a version number or a date)
 */
public class MaxwellContest
{
    private int blue;
    private int red;
    /**
     * Find the solution of the Maxwell Demon's problem
     * @param int h -> height of the container
     * @param int w -> width of the container
     * @param int d -> position of the demon (0,d)
     * @param int b -> amount of blue particles
     * @param int r -> amount of red particles
     * @param int [][] -> vector 2D of particles [px,py,vx,vy]
     * return the amount of ticks where all particles are in the right chamber.
       */
    public float solve(int h, int w,int d, int b, int r, int [][] particles){ //[PX,PY,VX,VY] - PX += VX, PY += VY
        blue = b;
        red = r;
        ArrayList<ArrayList<Integer>> parti = convertToArrayListArrayList(particles);
        //System.out.println(parti.get(0));
        //System.out.println(parti.get(1));
        int ticks = 0;
        int limit = 30;
        if (verifyIfIsDone(h,w,parti)){
            return (float)ticks;
        }
        while (ticks < limit){
            for (int j = 0; j < parti.size(); j++){
                ArrayList<Integer> particle = parti.get(j);
                int espeX = particle.get(0) + particle.get(2);
                int espeY = particle.get(1) + particle.get(3);
                //System.out.println("Antes de mover la partícula " + j + ": X = " + particle.get(0) + ", Y = " + particle.get(1) + ", VX = " + particle.get(2) + ", VY = " + particle.get(3));
                movement(h,w,d,particle,whereIs(particle.get(0)));
                //System.out.println("Después de mover la partícula " + j + ": X = " + particle.get(0) + ", Y = " + particle.get(1) + ", VX = " + particle.get(2) + ", VY = " + particle.get(3));
                //System.out.println(particle);
            }
            ticks++;
            //System.out.println("Estado de las partículas después del tick " + ticks + ": ");
            for (int k = 0; k < parti.size(); k++) {
                ArrayList<Integer> particle = parti.get(k);
                System.out.println("Partícula " + k + ": X = " + particle.get(0) + ", Y = " + particle.get(1) + ", VX = " + particle.get(2) + ", VY = " + particle.get(3));
            }
            if (verifyIfIsDone(h,w,parti)){
                //System.out.println("////////////////////////////////////////////////////////////////////////");
                System.out.println(ticks);
                return (float)ticks;
            }
        }
        //System.out.println("////////////////////////////////////////////////////////////////////////");
        System.out.println(ticks);

        if (ticks == limit){
            return (float)-1.0;
        }
        return (float)ticks;
    }
    /**
     * if exist a solution of the Maxwell Demon's problem make a simulation with maxwell container
     * @param int h -> height of the container
     * @param int w -> width of the container
     * @param int d -> position of the demon (0,d)
     * @param int b -> amount of blue particles
     * @param int r -> amount of red particles
     * @param int [][] -> vector 2D of particles [px,py,vx,vy]
       */
    public void simulate(int h,int w, int d, int b, int r, int [][] particles){ //PX PY VX VY
        float t = solve(h,w,d,b,r,particles);
        if (t != (float)-1.0){
            MaxwellContainer solution = new MaxwellContainer(h,w,d,b,r,particles);
            solution.makeVisible();
            solution.start((int)t);
        }
        else{
            System.out.println("impossible");
        }
    }
    /*
     * Makes the movement of the particle
     * @param int h -> height of the container
     * @param int w -> width of the container
     * @param int d -> position of the demon
     * @param ArrayList<Integer> particles -> 1D ArrayList [px,py,vx,vy]
     * @param boolean isRed -> define if the particle is red or not.
       */
    private void movement(int h, int w,int d, ArrayList<Integer> particles, boolean isRed){
        int velociX = particles.get(2);
        int velociY = particles.get(3);
        int espeX = particles.get(0) + particles.get(2);
        int espeY = particles.get(1) + particles.get(3);
        int x = particles.get(0);
        int y = particles.get(1);
        if (isInDemonPosition(d,espeX,espeY,x,y,velociX,velociY)){
            if(!(whereIs(espeX) == isRed)){
                particles.set(0,espeX);
                particles.set(1,espeY);
                return;
            }
        }
        boolean verify = verifyLimits(espeX,espeY, h, w, x, y) && (y + velociY >= 0);
        if (verify){
            //System.out.println("ENTRO");
            particles.set(0,espeX);
            particles.set(1,espeY);
        }
        else if (!verify){
            //System.out.println("SE SALE -> BOUNCE");
            bounce(h,w,particles);
        }
    }
    /*
     * verify limits of the container
     * @param int espeX -> is the position plus the velocityX
     * @param int espeY -> is the position plus the velocityY
     * @param int h -> height of the container
     * @param int w -> width of the container
     * @param int px -> position of the particle in x
     * @param int py -> position of the particle in y
       */
    private boolean verifyLimits(int espeX, int espeY, int h, int w, int px, int py){
        //System.out.println("VERIFY?");
        int auxXMin;
        int auxXMax;
        int auxYMin = 0;
        int auxYMax = h;
        if (whereIs(px)){
            auxXMin=-w;
            auxXMax=0;
            return  espeX >= auxXMin  && espeX <= auxXMax  && espeY >= auxYMin  && espeY <= auxYMax;
        }        
        if (!whereIs(px)){
            auxXMin=0;  
            auxXMax=w; 
            return espeX >= auxXMin  && espeX <= auxXMax  && espeY >= auxYMin  && espeY <= auxYMax;
        }
        return false;
    }
    /*
     * verify if is in the demon position
     * @param int d -> position of the demon
     * @param int espeX -> is the position plus the velocityX
     * @param int espeY -> is the position plus the velocityY
     * @param int px -> position of the particle in x
     * @param int py -> position of the particle in y
     * @param int vx -> velocity in x 
     * @param int vy -> velocity in y
       */
    private boolean isInDemonPosition(int d, int espeX, int espeY, int x, int y, int vx, int vy){
        boolean isLeft = whereIs(x);
        if (espeX==0 && espeY == d){
                    return true;
            }
        if (isLeft){
            if (vy <0){
                while (x < espeX && y< espeY){
                    x +=1;
                    y -=1;
                    if (x==0 && y== d){
                    return true;
                    }
                }
            }
            else if (vy >0){
                while (x< espeX && y< espeY){
                    x +=1;
                    y +=1;
                    if (x==0 && y== d){
                        return true;
                    }
                }
            }
            else if (vy == 0){
                while (x < espeX && y == espeY){
                    x += 1;
                    if (x == 0 && y == d){
                        return true;
                    }
                }
            }
        }
        if (!isLeft){
            if (vy <0){
                while (x< espeX && y< espeY){
                    x -=1;
                    y -=1;
                if (x==0 && y== d){
                    return true;
                }
            }
            }
            else if (vy >0){
                while (x< espeX && y< espeY){
                    x -=1;
                    y +=1;
                    if (x==0 && y== d){
                        return true;
                    }
                }
            }
            else if (vy == 0){
                while (x > espeX && y == espeY){
                    x -= 1;
                    if (x == 0 && y == d){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /*
     * Find where is the particle (left - right)
     * @param int x -> position in x of the particle
       */
    private boolean whereIs(int x){
        return x < 0;
    }
    /*
     * verify where the bounce is doing
     * @param int h -> height of the container
     * @param int w -> width of the container
     * @param ArrayList<Integer> -> list of particles [px,py,vx,vy]
       */
    private void bounce(int h, int w, ArrayList<Integer> particles){
        if (whereIs(particles.get(0))){
            bounceLeft(h,w,particles);
        }
        if (!whereIs(particles.get(0))){
            bounceRight(h,w,particles);
        }
    }
    /*
     * do the bounce in the corner
     * @param ArrayList<Integer> -> list of particles [px,py,vx,vy]
       */
    private void bounceInCorner(ArrayList<Integer> particles){
        particles.set(2, -particles.get(2));
        particles.set(3, -particles.get(3));
    }
    /*
     * Makes the bounce in the right
     * @param int h -> height of the container
     * @param int w -> width of the container
     * @param ArrayList<Integer> -> list of particles [px,py,vx,vy]
       */
    private void bounceRight(int h, int w, ArrayList<Integer> particles){
        int velociX = particles.get(2);
        int velociY = particles.get(3);
        int espeX = particles.get(0) + particles.get(2);
        int espeY = particles.get(1) + particles.get(3);
        int x = particles.get(0);
        int y = particles.get(1);
        if ((espeX == 0  || espeX == w) && (espeY == 0  || espeY == h)){
            bounceInCorner(particles);
        }
        else if ((espeX > w ) && (espeY > h)){
            particles.set(0,w);
            particles.set(1,h);
            bounceInCorner(particles);
        }
        else if ((espeX > w) && (espeY < 0)){
            particles.set(0,w);
            particles.set(1,0);
            bounceInCorner(particles);
        }
        else if ((espeX < 0 ) && (espeY > h)){
            particles.set(0,0);
            particles.set(1,h);
            bounceInCorner(particles);
        }
        else if ((espeX < 0) && (espeY < 0)){
            particles.set(0,0);
            particles.set(1,h);
            bounceInCorner(particles);
        }
        if (espeX >= w){ // PARED DERECHA
                float t = ((float)(w - x) / velociX);
                int  n = ((int)(y + (velociY * t)));
                particles.set(0,w);
                particles.set(1,n);
                particles.set(2,-velociX);
        }
        else if (espeX <= 0 && (espeY <= h && espeY >= 0)){ // PARED IZQUIERDA
                float t = -((float)(x-0) / velociX);
                int  n = ((int)(y + (velociY * t)));
                particles.set(0,0);
                particles.set(1,n);
                particles.set(2,-velociX);
        }
        else if ((espeY < 0) && (espeX >= 0 && espeX <= w)){ // - PISO
            float t = -((float) (y-0) / velociY);
            int  n = ((int)(x + (velociX * t)));
            particles.set(0,n);
            particles.set(1,0);
            particles.set(3,velociY); // termina siendo la y positiva
        }
        else if ((espeY >= h) && (espeX >= 0 && espeX <= w)){ // TECHO
            float t = (float)(y-0)/velociY;
            int n = ((int)(x+ (velociX * t)));
            particles.set(0,n);
            particles.set(1,h);
            particles.set(3, -velociY);// termina siendo la y positiva
        }
    }
    /*
     * Makes the bounce in the left
     * @param int h -> height of the container
     * @param int w -> width of the container
     * @param ArrayList<Integer> -> list of particles [px,py,vx,vy]
       */
    private void bounceLeft(int h, int w, ArrayList<Integer> particles){
        int espeX = particles.get(0) + particles.get(2);
        int espeY = particles.get(1) + particles.get(3);
        int x = particles.get(0);
        int y = particles.get(1);
        int velociX = particles.get(2);
        int velociY = particles.get(3);
        if ((espeX == 0 || espeX == -w) && (espeY == 0  || espeY == h)){
            bounceInCorner(particles);
        }
        else if ((espeX > 0 ) && (espeY > h)){
            particles.set(0,0);
            particles.set(1,h);
            bounceInCorner(particles);
        }
        else if ((espeX > 0 ) && (espeY < 0)){
            particles.set(0,0);
            particles.set(1,0);
            bounceInCorner(particles);
        }
        else if ((espeX < -w) && (espeY > h)){
            particles.set(0, -w);
            particles.set(1, h);
            bounceInCorner(particles);
        }
        else if ((espeX < -w) && (espeY < 0)){
            particles.set(0, -w);
            particles.set(1, 0);
            bounceInCorner(particles);
        }
        else if (espeX <= -w){ // PARED
             if (velociX< 0 && velociY < 0 || (velociX < 0 && velociY > 0)){
                float t = ((float)-(w + x)/ velociX); // aa
                int  n = ((int)(y + (velociY * t)));
                particles.set(0, -w);
                particles.set(1, n);
                particles.set(2, -velociX);
            }
        }
        else if (espeX >= 0 && (espeY <= h && espeY >= 0)){ // PAREDES
            if (velociX > 0 && velociY > 0  || velociX > 0 && velociY < 0){
                float t = ((float)(0-x) / velociX);
                int  n =  ((int)(y + (velociY * t)));
                particles.set(0, 0);
                particles.set(1, n);
                particles.set(2, -velociX); // termina siendo la X negativa
            }
        }
        else if((espeY < 0 && ((espeX <= 0) && espeX >= -w))){ // PISO
            if(velociX > 0 && velociY < 0 || velociX < 0 && velociY < 0 ){
                float t = -((float) (y-0) / velociY);
                int  n = ((int)(x + (velociX * t)));
                particles.set(0, n);
                particles.set(1,0);
                particles.set(3, -velociY);
            }
        }
        else if ((espeY >= h) && (espeX <= 0 && espeX >= -w)){
            if (velociX > 0 && velociY > 0 || velociX < 0 && velociY > 0 ){
                float t = (float)(y-0)/velociY;
                int n = ((int)(x+ (velociX * t)));
                particles.set(0,n);
                particles.set(1,h);
                particles.set(3, -velociY);
            }
        }
    }
    /*
     * verify if is red particles are in the left chamber and all blue particles are in the right chamber
     * @param int h -> height of container
     * @param int w -> width of container
     * @param ArrayList<ArrayList<Integer>> particles -> List2D of particles [px,py,vx,vy]
       */
    private boolean verifyIfIsDone(int h, int w, ArrayList<ArrayList<Integer>> particles) {
        boolean verify = true;
        for (int i = 0; i < blue; i++){
            int x = particles.get(i).get(0);
            if (x < 0 || x > w) {
                verify = false;
                break;
            }
        }
        for (int i = blue; i < blue + red; i++){
            int x = particles.get(i).get(0);
            if (x < -w || x > 0) {
                verify = false;
                break;
            }
        }
        return verify;
    }
    public static void main(String args[]){
        int [][] vector2D = {{-3,1,2,0},{2,1,-4,0}};
        int [][] vector2D1 = {{3,1,2,2},{-2,3,-2,-1},{3,2,1,-2},{-2,2,2,2}};
        int [][] vector2D2 = {{-1,1,2,2}};
        int [][] vector2D3 = {{-3,4,-1,-1}};
        MaxwellContest b = new MaxwellContest();
        //System.out.println(b.solve(4,7,1,1,1,vector2D));
        //System.out.println(b.solve(4,4,1,2,2,vector2D1));
        //System.out.println(b.solve(4,4,2,1,0,vector2D2));
        System.out.println(b.solve(5,4,1,1,0,vector2D3));
    }
    /*
     * Method that convert to ArrayList of ArrayList
     * @param int [][] m -> vector 2D
       */
    private ArrayList<ArrayList<Integer>> convertToArrayListArrayList(int [][] m){
        ArrayList<ArrayList<Integer>> l = new ArrayList<>();
        for (int [] fila : m){
            ArrayList<Integer> f = new ArrayList<>();
            for (int e : fila){
                f.add(e);
            }
            l.add(f);
        }
        return l;
    }
}
