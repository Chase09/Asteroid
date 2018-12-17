import greenfoot.*;

/**
 * A rock in space.
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 */
public class Asteroid extends SmoothMover
{
    //TODO (26): Declare an integer instance variable called size
    /** Size of this asteroid */
    private int size;

    //TODO (27): Declare an integer instance variable called stability
    /** When the stability reaches 0 the asteroid will explode */
    private int stability;

    /**
     * This is the default constructor for objects of type Asteroid
     * 
     * @param None There are no parameters
     * @return Nothing is returned
     */
    public Asteroid()
    {
        this(60);
    }

    /**
     * Asteroid is the constructor for objects of type Asteroid.
     * It allows customization of the size of the Asteroid
     * 
     * @param size represents the size of the Asteroid
     * @return An object of type Asteroid
     */
    public Asteroid(int size)
    {
        super(new Vector(Greenfoot.getRandomNumber(360), 2));

        //TODO (32): Delete this line and make a call to the setSize method instead
        setSize(size);
    }

    /**
     * Asteroid is the constructor for objects of type Asteroid.
     * It allows customization of the size of the Asteroid and
     * the speed and direction of the Asteroid
     * 
     * @param size represents the size of the Asteroid
     * @param velocity represents the speed and direction of the Asteroid
     * @return An object of type Asteroid
     */
    public Asteroid(int size, Vector velocity)
    {
        super(velocity);

        //TODO (33): Delete this line and make a call to the setSize method instead
        getImage().scale(size, size);
    }

    /**
     * act is the method that is run on every 
     * iteration of the act cycle
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void act()
    {         
        move();
    }

    /**
     * This method sets the size of the asteroids
     * that spawn in the world.
     *           
     * @param There is an integer parameter called size
     * @return Nothing is being returned
     */
    //TODO (28): Declare a method called setSize that does not return anything and has an integer parameter called size
    private void setSize(int size)
    {
        //TODO (29): Initialize stability to the size parameter
        stability = size;
        //TODO (30): Initialize the size instance variable to the size parameter
        this.size = size;
        //TODO (31): Scale the image of this Asteroid to a width of size and a height of size
        getImage().scale(size,size);
    }

    /**   
     * This method makes it so that the objects in
     * the world take damge from getting hit.
     *          
     * @param There is an integer parameter called damage         
     * @return Nothing is being returned     
     */
    //TODO (51): Declare a public method called hit that does not return anything and has an integer parameter called damage.
    public void hit(int damage)
    {
        //TODO (52): Declare a local Space variable called space that stores a reference to the world.
        Space space = (Space)getWorld();
        //TODO (53): Decrease stability by damage.
        stability = stability - damage;
        //TODO (54): If stability of the asteroid is less than or equal to 0...
        if(stability <= 0)
        {
            //TODO (55): If the size of the asteroid is greater than or equal to 50...
            if(size >= 50)
            {
                //TODO (56): Use the reference to the world to count 15 points to the score
                space.countScore(15);
            }
            //TODO (57): Otherwise, if the size of the asteroid is greater than or equal to 25...
            else if(size >= 25)
            {
                //TODO (58): Use the reference to the world to count 30 points to the score
                space.countScore(30);
            }
        }
            //TODO (59): Otherwise...
            else
            {
                //TODO (60): Use the reference to the world to count 50 points to the score
                space.countScore(50);
                
            }
            //TODO (61): Break up the asteroid into a number of pieces that is 5 or fewer
            breakUp(5);
    }
        
    /**
     * 
     *               
     * @param There is an integer parameter called numBreakUp         
     * @return Nothing is being returned    
     */
    //TODO (34): Declare a method called breakUp that does not return anything and has an integer parameter called numBreakUp

    private void breakUp( int numBreakUp)
    {
        //TODO (35): Declare a local integer variable called rotation
        int rotation;
        //TODO (36): Declare a local double variable called length
        double length;
        //TODO (37): Declare a local Vector variable called speed
        Vector speed;
        //TODO (38): Declare a local Asteroid variable called debris
        Asteroid debris;
        //TODO (39): Play the Explosion.wav sound
        Greenfoot.playSound("Explosion.wav");
        //TODO (40): If the size of the asteroid is less than or equal to 15...
        if(size <= 15)
        {
            //TODO (41): Remove this object
            getWorld().removeObject(this);
        }
        //TODO (42): Otherwise...
        else
        {
            //TODO (43): Initialize rotation to getVelocity().getDirection() + a random number between 0 and 45
            rotation = getVelocity().getDirection() + Greenfoot.getRandomNumber(45);
            //TODO (44): Initialize length to getVelocity().getLength()
            length = getVelocity().getLength();
            //TODO (45): Use a loop that will run as many times as the asteroid needs to break up
            for( int i = 0; i < numBreakUp; i++ )
            {
                //TODO (46): Inside the loop, initialize speed to a new Vector that uses direction plus a random number between -100 and 100 and length times 1.2
                speed = new Vector(rotation + Greenfoot.getRandomNumber(200)-100,length * 1.2);
                //TODO (47): Initialize debris to a new Asteroid that uses size divided by the number of break ups as one parameter and speed as the second parameter
                debris = new Asteroid(size/numBreakUp,speed);
                //TODO (48): Add the debris object at the current X location and the current Y location
                getWorld().addObject(debris,getX(),getY());
                //TODO (49): Access the move method of debris
                debris.move();
                
            }
            //TODO (50): Underneath the for loop, remove this object
            getWorld().removeObject(this);
            
        }
    }
}
