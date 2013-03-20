/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verplaatsingensysteem;

/**
 *
 * @author Leslie Aerts
 */
public class Lane
{

    private Edge beginEdge;
    private Edge endEdge;

    public Lane(Edge edge1, Edge edge2)
    {
        this.beginEdge = edge1;
        this.endEdge = edge2;
    }

    public Edge getBeginEdge()
    {
        return beginEdge;
    }

    public void setBeginEdge(Edge beginEdge)
    {
        this.beginEdge = beginEdge;
    }

    public Edge getEndEdge()
    {
        return endEdge;
    }

    public void setEndEdge(Edge endEdge)
    {
        this.endEdge = endEdge;
    }
}
