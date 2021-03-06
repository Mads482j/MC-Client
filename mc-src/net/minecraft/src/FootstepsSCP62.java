package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class FootstepsSCP62 extends Packet62LevelSound
{
	/** e.g. step.grass */
	private String soundName;
	public int field_73577_b;
	public int field_73578_c = Integer.MAX_VALUE;
	public int field_73575_d;
	
	/** 1 is 100%. Can be more. */
	public float volume;
	
	/** 63 is 100%. Can be more. */
	public int pitch;
	
	public FootstepsSCP62()
	{
	}
	
	public FootstepsSCP62(String par1Str, double par2, double par4, double par6, float par8, float par9)
	{
		this.soundName = par1Str;
		this.field_73577_b = (int) (par2 * 8.0D);
		this.field_73578_c = (int) (par4 * 8.0D);
		this.field_73575_d = (int) (par6 * 8.0D);
		this.volume = par8;
		this.pitch = (int) (par9 * 63.0F);
		
		if (this.pitch < 0)
		{
			this.pitch = 0;
		}
		
		if (this.pitch > 255)
		{
			this.pitch = 255;
		}
	}
	
	/**
	 * Abstract. Reads the raw packet data from the data stream.
	 */
	@Override
	public void readPacketData(DataInputStream par1DataInputStream) throws IOException
	{
		this.soundName = readString(par1DataInputStream, 32);
		this.field_73577_b = par1DataInputStream.readInt();
		this.field_73578_c = par1DataInputStream.readInt();
		this.field_73575_d = par1DataInputStream.readInt();
		this.volume = par1DataInputStream.readFloat();
		this.pitch = par1DataInputStream.readUnsignedByte();
		
		// Catch all footsteps and cancel them
		if (this.volume == 0.15f && this.pitch == 63)
		{
			this.volume = 0;
		}
		
	}
	
	/**
	 * Abstract. Writes the raw packet data to the data stream.
	 */
	@Override
	public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
	{
		writeString(this.soundName, par1DataOutputStream);
		par1DataOutputStream.writeInt(this.field_73577_b);
		par1DataOutputStream.writeInt(this.field_73578_c);
		par1DataOutputStream.writeInt(this.field_73575_d);
		par1DataOutputStream.writeFloat(this.volume);
		par1DataOutputStream.writeByte(this.pitch);
	}
	
	@Override
	public String getSoundName()
	{
		return this.soundName;
	}
	
	@Override
	public double getEffectX()
	{
		return this.field_73577_b / 8.0F;
	}
	
	@Override
	public double getEffectY()
	{
		return this.field_73578_c / 8.0F;
	}
	
	@Override
	public double getEffectZ()
	{
		return this.field_73575_d / 8.0F;
	}
	
	@Override
	public float getVolume()
	{
		return this.volume;
	}
	
	@Override
	public float getPitch()
	{
		return this.pitch / 63.0F;
	}
	
	@Override
	public void processPacket(NetHandler par1NetHandler)
	{
		par1NetHandler.handleLevelSound(this);
	}
	
	@Override
	public int getPacketSize()
	{
		return 24;
	}
	
}
