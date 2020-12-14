/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author charl
 */
public class Coord{
    
    private int m_x;
    private int m_y;
    
 /**
 * @param x
 * @param y
 */
    public Coord( int x, int y){
        m_x = x;
        m_y = y;
    }
    
 /**
 * position en x
 * @return 
 */
    public int getX (){
        return m_x;
    }
    
 /**
 * position en y
 * @return 
 */
    public int getY(){
        return m_y;
    }
}
