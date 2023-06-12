package lol.waifuware.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.util.math.MathHelper;
// made by Coffee client https://github.com/Coffee-Client/Coffee
@AllArgsConstructor
@Setter
@Getter
public class Rotation implements Cloneable {
    private float pitch, yaw;

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public Rotation(float pitch, float yaw){
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Rotation normalize() {
        this.pitch = MathHelper.wrapDegrees(this.pitch);
        this.yaw = MathHelper.wrapDegrees(this.yaw);
        return this;
    }

    @Override
    public Rotation clone() {
        try {
            return (Rotation) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
