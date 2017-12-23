package advent.util.day20;

public class Point {
    private Integer pX;
    private Integer pY;
    private Integer pZ;

    private Integer vX;
    private Integer vY;
    private Integer vZ;

    private Integer aX;
    private Integer aY;
    private Integer aZ;

    /*  Read String in format:
     *  p=< 3,0,0>, v=< 2,0,0>, a=<-1,0,0>
     */
    public Point(String input) {
        String[] split = input.split(",\\s");
        String[] pSplit = split[0].substring(3, split[0].length() - 1).split(",");
        String[] vSplit = split[1].substring(3, split[1].length() - 1).split(",");
        String[] aSplit = split[2].substring(3, split[2].length() - 1).split(",");
        pX = Integer.parseInt(pSplit[0].trim());
        pY = Integer.parseInt(pSplit[1].trim());
        pZ = Integer.parseInt(pSplit[2].trim());
        vX = Integer.parseInt(vSplit[0].trim());
        vY = Integer.parseInt(vSplit[1].trim());
        vZ = Integer.parseInt(vSplit[2].trim());
        aX = Integer.parseInt(aSplit[0].trim());
        aY = Integer.parseInt(aSplit[1].trim());
        aZ = Integer.parseInt(aSplit[2].trim());
    }

    public Integer sumA() {
        return Math.abs(aX) + Math.abs(aY) + Math.abs(aZ);
    }

    public Integer getpX() {
        return pX;
    }
    public void setpX(Integer pX) {
        this.pX = pX;
    }
    public Integer getpY() {
        return pY;
    }
    public void setpY(Integer pY) {
        this.pY = pY;
    }
    public Integer getpZ() {
        return pZ;
    }
    public void setpZ(Integer pZ) {
        this.pZ = pZ;
    }
    public Integer getvX() {
        return vX;
    }
    public void setvX(Integer vX) {
        this.vX = vX;
    }
    public Integer getvY() {
        return vY;
    }
    public void setvY(Integer vY) {
        this.vY = vY;
    }
    public Integer getvZ() {
        return vZ;
    }
    public void setvZ(Integer vZ) {
        this.vZ = vZ;
    }
    public Integer getaX() {
        return aX;
    }
    public void setaX(Integer aX) {
        this.aX = aX;
    }
    public Integer getaY() {
        return aY;
    }
    public void setaY(Integer aY) {
        this.aY = aY;
    }
    public Integer getaZ() {
        return aZ;
    }
    public void setaZ(Integer aZ) {
        this.aZ = aZ;
    }

    public void update() {
        vX += aX;
        vY += aY;
        vZ += aZ;
        pX += vX;
        pY += vY;
        pZ += vZ;
    }

    public String printPos() {
        return pX.toString() + " " + pY.toString() + " " + pZ.toString();
    }

    @Override
    public int hashCode(){
        return pX.intValue() ^ pY.intValue() ^ pZ.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Point)) {
            return false;
        }
        Point other = (Point) obj;
        return this.pX.equals(other.pX) && this.pY.equals(other.pY) && this.pZ.equals(other.pZ);
    }
}
