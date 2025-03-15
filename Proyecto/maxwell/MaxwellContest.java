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
    public float solve(int h, int w,int d, int b, int r, ArrayList<ArrayList<Integer>> particles){ //[PX,PY,VX,VY] - PX += VX, PY += VY
        int ticks = 0;
        if (verifyIfIsDone(b,r,particles)){
            return (float)ticks;
        }
        for (int i = 0; i < 1000; i++){
            for (int j = 0; j < particles.size(); j++){
                ArrayList<Integer> particle = particles.get(j);
                int espeX = particle.get(0) + particle.get(2);
                int espeY = particle.get(1) + particle.get(3);
                if ((espeX > w || espeX < -w) && (espeY > h || espeY < h)){
                    particle.set(0, espeX); 
                    particle.set(1, espeY);
                }
                else{
                    bounce(h,w,particle);
                }
            }
            ticks++;
            if (verifyIfIsDone(b,r,particles)){
                return (float)ticks;
            }
        }
        return (float)ticks;
    }
    public float simulate(int h,int w, int d, int b, int r, ArrayList<ArrayList<Integer>> particles){
        return 0.0F;
    }
    private void bounce(int h, int w, ArrayList<Integer> particles){
        if ((particles.get(0) == 0 || particles.get(0) == w || particles.get(0) == -w) && (particles.get(1) == 0 || particles.get(1) == h)){
            particles.set(2, -particles.get(2));
            particles.set(3, -particles.get(3));
        }
        else if ((particles.get(1) >= h) && (particles.get(0) <= w || particles.get(0) >= -w)){
            float t = (float)(h - particles.get(1))/particles.get(3);
            particles.set(0, particles.get(1) + (int)(particles.get(3)*t));
            particles.set(1, h);
            particles.set(3, -particles.get(3));
        }
        else if ((particles.get(1) <= h) && (particles.get(0) <= w || particles.get(0) >= -w)){
            float t = (float)(0 - particles.get(1))/particles.get(3);
            particles.set(0, particles.get(1) + (int)(particles.get(3)*t));
            particles.set(1, 0);
            particles.set(2, -particles.get(2));
        }
        else if ((particles.get(0) <= -w) && (particles.get(1) >= 0 || particles.get(1) <= h)){
            float t  = (float)(-w - particles.get(0))/particles.get(2);
            particles.set(0, -w);
            particles.set(1, particles.get(1) + (int)(particles.get(3)*t));
            particles.set(2, -particles.get(2));
        }
        else if ((particles.get(0) >= 0) && (particles.get(1) >= 0 || particles.get(1) <= h)){
            float t = (float)(0);
            particles.set(2, -particles.get(2));
        }
        else if((particles.get(0) >= w) && (particles.get(1) >= 0 || particles.get(1) <= h)){
            float t = (float)(w - particles.get(0))/particles.get(2);
            particles.set(0, w);
            particles.set(1, particles.get(1) + (int)(particles.get(2)*t));
            particles.set(2, -particles.get(2));
        }
        else if((particles.get(0) <= 0) && (particles.get(1) >= 0 || particles.get(1) <= h)){
            float t = (float)(0-particles.get(0))/particles.get(2);
            particles.set(0,0);
            particles.set(1, particles.get(1) + (int)(particles.get(2)*t));
            particles.set(2, -particles.get(2));
        }
    }
    private boolean verifyIfIsDone(int b, int r, ArrayList<ArrayList<Integer>> particles){
        int total = b+r-1;
        boolean verify = true;
        while(total > 0){
            particles.get(total).get(0);
            if (total <= r){
                //empezamos las azules
                if (!(particles.get(total).get(0) >= 0)){
                    verify = false;
                }
            }
            //Rojas
            if (!(particles.get(total).get(0) <= 0)){
                verify = false;
            }
            total -= 1;
        }
        return verify;
    }
}
