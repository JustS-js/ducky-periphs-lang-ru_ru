package com.samsthenerd.duckyperiphs.peripherals.sculkophone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.world.event.GameEvent;

public class SculkophonePeripheral  implements IPeripheral {
    public static int DEFAULT_RANGE = 8;
    
    private final SculkophoneBlockEntity sBEntity;

    public Set<IComputerAccess> computers = Collections.newSetFromMap(new ConcurrentHashMap<>());
    
    SculkophonePeripheral(SculkophoneBlockEntity SBE){
        sBEntity = SBE;
    }
    
    @Nonnull
    @Override
    public String getType() {
        return "sculkophone";
    }
    
    @Override
    public boolean equals(@Nullable IPeripheral other) {
        return this == other;
    }

    @Override
    public void attach(@Nonnull IComputerAccess computer) {
        computers.add(computer);
    }

    @Override
    public void detach(@Nonnull IComputerAccess computer) {
        computers.remove(computer);
    }

    public void vibrationEvent(GameEvent vibrationEvent, double distance){
        for(IComputerAccess computer : computers){
            computer.queueEvent("vibration", computer.getAttachmentName(), vibrationEvent.getId(), distance);
        }
    }

}

