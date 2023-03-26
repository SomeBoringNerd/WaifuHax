package lol.waifuware.Events;

import meteordevelopment.orbit.ICancellable;
import net.minecraft.network.packet.Packet;

public class OnPacketEvent
{
    public static class Receive implements ICancellable {
        private static final Receive INSTANCE = new Receive();

        public Packet<?> packet;

        public static Receive get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.packet = packet;
            return INSTANCE;
        }

        @Override
        public void setCancelled(boolean cancelled) {

        }

        @Override
        public boolean isCancelled() {
            return false;
        }
    }

    public static class Send implements ICancellable {
        private static final Send INSTANCE = new Send();

        public Packet<?> packet;

        public static Send get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.packet = packet;
            return INSTANCE;
        }

        @Override
        public void setCancelled(boolean cancelled) {

        }

        @Override
        public boolean isCancelled() {
            return false;
        }
    }

    public static class Sent {
        private static final Sent INSTANCE = new Sent();

        public Packet<?> packet;

        public static Sent get(Packet<?> packet) {
            INSTANCE.packet = packet;
            return INSTANCE;
        }
    }
}
