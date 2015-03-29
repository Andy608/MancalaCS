package com.csmancala.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class TransformImage {
	/**
	 * rotateImage takes in a BufferedImage and the amount of degrees to rotate the image. Returns the new rotated image.
	 * NOTE: The size of the BufferedImage might be changed depending on the degrees rotated. Take this into account.
	 * @param bi : The original BufferedImage.
	 * @param degrees : The degrees you want rotated. The method converts this number to radians for you. If the number sent in is negative, 
	 * the rotation will occur counter-clockwise.
	 * @return : A new BufferedImage that is rotated the amount of degrees specified. 
	 */
	public static BufferedImage rotateImage(BufferedImage bi, float degrees) {
		
		double cos = Math.abs(Math.cos(Math.toRadians(degrees)));
        double sin = Math.abs(Math.sin(Math.toRadians(degrees)));
        double width = bi.getWidth();
        double height = bi.getHeight();
        int w = (int)(width * cos + height * sin);
        w = (w % 2 != 0) ? w + 1 : w;
        
        int h = (int)(width * sin + height * cos);
        h = (h % 2 != 0) ? h + 1 : h;
        
        BufferedImage rotatedImage = new BufferedImage(w, h, bi.getType());
        Graphics2D g2 = rotatedImage.createGraphics();
        
        double x = w / 2;
        double y = h / 2;
        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(degrees), x, y);
        x = (w - width) / 2;
        y = (h - height) / 2;
        at.translate(x, y);
        g2.drawRenderedImage(bi, at);
        g2.dispose();
        return rotatedImage;
	}
	
	//Thanks to http://scaleimagesjava.blogspot.com/2011/09/scale-images-in-java.html for the amazing scaling method.
		public static BufferedImage scaleImage(BufferedImage img, int targetWidth, int targetHeight, Object hint, boolean higherQuality) {
			int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
			BufferedImage ret = (BufferedImage) img;
			int w, h;
			
			if (higherQuality) {
				// Use multi-step technique: start with original size, then
				// scale down in multiple passes with drawImage()
				// until the target size is reached
				w = img.getWidth();
				
				if (w < targetWidth) {
					w = targetWidth;
				}
				
				h = img.getHeight();
				
				if (h < targetHeight) {
					h = targetHeight;
				}
			}
			else {
				// Use one-step technique: scale directly from original
				// size to target size with a single drawImage() call
				w = targetWidth;
				h = targetHeight;
			}

			do {
				if (higherQuality && w > targetWidth) {
					w >>= 1;
					if (w < targetWidth) {
						w = targetWidth;
					}
				}

				if (higherQuality && h > targetHeight) {
					h >>= 1;
					if (h < targetHeight) {
						h = targetHeight;
					}
				}

				BufferedImage tmp = new BufferedImage(w, h, type);
				Graphics2D g2 = tmp.createGraphics();
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
				g2.drawImage(ret, 0, 0, w, h, null);

				ret = tmp;
			} while (w != targetWidth || h != targetHeight);

			return ret;
		}
}
